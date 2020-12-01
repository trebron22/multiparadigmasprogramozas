package hu.eke.multiparadigmasprogramozasinyelvekbeadando.controller;

import hu.eke.multiparadigmasprogramozasinyelvekbeadando.config.GeoLocationConfig;
import hu.eke.multiparadigmasprogramozasinyelvekbeadando.dao.Address;
import hu.eke.multiparadigmasprogramozasinyelvekbeadando.dao.GeoLocationSearch;
import hu.eke.multiparadigmasprogramozasinyelvekbeadando.dto.request.MultiParadigmasProgramozasiNyelvekBeadandoRequest;
import hu.eke.multiparadigmasprogramozasinyelvekbeadando.exception.DoNotExistObjectException;
import hu.eke.multiparadigmasprogramozasinyelvekbeadando.exception.MultiParadigmasProgramozasiNyelvekBeadandoRequestIsEmptyException;
import hu.eke.multiparadigmasprogramozasinyelvekbeadando.dto.Result;
import hu.eke.multiparadigmasprogramozasinyelvekbeadando.dto.response.GeoLocationResponse;
import hu.eke.multiparadigmasprogramozasinyelvekbeadando.services.MultiiParadigmasBeadandoService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.nio.charset.Charset;
import java.util.Collections;
import java.util.List;

@RestController
public class WebClientController {

    private static final Log logger = LogFactory.getLog(WebClientController.class);

    @Autowired
    GeoLocationConfig geoLocationConfig;

    @Autowired
    MultiiParadigmasBeadandoService multiiParadigmasBeadandoService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/geolocation/{geoLocationId}")
    public List<Address> getAddress(@PathVariable Long geoLocationId) throws DoNotExistObjectException {
        logger.info("getGeoLocationSearch method was called with: " + geoLocationId + " parameter");
        return multiiParadigmasBeadandoService.getAddress(geoLocationId);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/address")
    public ResponseEntity<Address> updateEmployee(@RequestBody Address employee) {
        logger.info("updateEmployee method was called with: " + employee + " parameter");
        Address address = multiiParadigmasBeadandoService.editEmployees(employee);
        return new ResponseEntity<>(address, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/employee")
    public ResponseEntity<String> deleteEmployee(@RequestParam(name = "addressId") Long addressId) {
        logger.info("deleteEmployee method was called with: " + addressId + " parameter");
        multiiParadigmasBeadandoService.deleteAddress(addressId);
        return new ResponseEntity<>("Employee with ID :" + addressId + " deleted successfully", HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/geolocation")
    public GeoLocationResponse getGeoLocation(@RequestBody MultiParadigmasProgramozasiNyelvekBeadandoRequest multiParadigmasProgramozasiNyelvekBeadandoRequest) throws MultiParadigmasProgramozasiNyelvekBeadandoRequestIsEmptyException {
        logger.info("getGeoLocation method was called with: " + multiParadigmasProgramozasiNyelvekBeadandoRequest.getAddresses().size() + " parameters");

        if (multiParadigmasProgramozasiNyelvekBeadandoRequest.getAddresses().isEmpty() || (multiParadigmasProgramozasiNyelvekBeadandoRequest.getAddresses().size() == 1 && StringUtils.isEmpty(multiParadigmasProgramozasiNyelvekBeadandoRequest.getAddresses().get(0)))) {
            throw new MultiParadigmasProgramozasiNyelvekBeadandoRequestIsEmptyException("Request was empty");
        }

        WebClient.RequestBodySpec geoLocationUri = getRequestBodySpec(multiParadigmasProgramozasiNyelvekBeadandoRequest);
        WebClient.ResponseSpec response = getResponseSpec(geoLocationUri);
        if (response.bodyToMono(GeoLocationResponse.class).block().getStatus().equals("OK")) {
            multiiParadigmasBeadandoService.saveSearchParameters(multiParadigmasProgramozasiNyelvekBeadandoRequest);
        }

        return response.bodyToMono(GeoLocationResponse.class).block();
    }

    private WebClient.ResponseSpec getResponseSpec(WebClient.RequestBodySpec geoLocationUri) {
        WebClient.ResponseSpec response = geoLocationUri
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML)
                .acceptCharset(Charset.forName("UTF-8"))
                .retrieve();
        return response;
    }

    private WebClient.RequestBodySpec getRequestBodySpec(MultiParadigmasProgramozasiNyelvekBeadandoRequest multiParadigmasProgramozasiNyelvekBeadandoRequest) {
        WebClient.RequestBodySpec geoLocationUri = createWebClientWithServerURLAndDefaultValues().method(HttpMethod.POST)
                .uri(geoLocationConfig.getURI()
                        + String.join(",", multiParadigmasProgramozasiNyelvekBeadandoRequest.getAddresses())
                        + "&key="
                        + geoLocationConfig.getAPI_KEY()
                );
        return geoLocationUri;
    }

    public org.springframework.web.reactive.function.client.WebClient createWebClientWithServerURLAndDefaultValues() {
        logger.info("createWebClientWithServerURLAndDefaultValues method was called");
        return org.springframework.web.reactive.function.client.WebClient.builder()
                .baseUrl(geoLocationConfig.getBASE_URL())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultUriVariables(Collections.singletonMap("url", geoLocationConfig.getBASE_URL()))
                .build();
    }

    @ExceptionHandler(value = MultiParadigmasProgramozasiNyelvekBeadandoRequestIsEmptyException.class)
    public GeoLocationResponse multiParadigmasProgramozasiNyelvekBeadandoRequestIsEmptyException(MultiParadigmasProgramozasiNyelvekBeadandoRequestIsEmptyException exception) {
        logger.info("multiParadigmasProgramozasiNyelvekBeadandoRequestIsEmptyException method was called with: " + exception.getMessage() + " message");
        GeoLocationResponse geoLocationResponse = new GeoLocationResponse();
        geoLocationResponse.setResults(new Result[0]);
        geoLocationResponse.setStatus("ZERO_RESULTS");
        return geoLocationResponse;
    }

    @ExceptionHandler(value = DoNotExistObjectException.class)
    public Address doNotExistObjectException(DoNotExistObjectException exception) {
        logger.info("doNotExistObjectException method was called with: " + exception.getMessage() + " message");
        Address address = new Address();
        address.setSearch("");
        address.setGeoLocationSearch(new GeoLocationSearch());
        return address;
    }

    @ExceptionHandler(value = NumberFormatException.class)
    public Address numberFormatException(NumberFormatException exception) {
        logger.info("numberFormatException method was called with: " + exception.getMessage() + " message");
        Address address = new Address();
        address.setSearch("");
        address.setGeoLocationSearch(new GeoLocationSearch());
        return address;
    }

}
