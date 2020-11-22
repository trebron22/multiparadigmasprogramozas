package hu.eke.multiparadigmasprogramozasinyelvekbeadando.controller;

import hu.eke.multiparadigmasprogramozasinyelvekbeadando.config.GeoLocationConfig;
import hu.eke.multiparadigmasprogramozasinyelvekbeadando.dto.request.MultiParadigmasProgramozasiNyelvekBeadandoRequest;
import hu.eke.multiparadigmasprogramozasinyelvekbeadando.exception.MultiParadigmasProgramozasiNyelvekBeadandoRequestIsEmptyException;
import hu.eke.multiparadigmasprogramozasinyelvekbeadando.dto.Result;
import hu.eke.multiparadigmasprogramozasinyelvekbeadando.dto.response.GeoLocationResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.nio.charset.Charset;
import java.time.ZonedDateTime;
import java.util.Collections;

@RestController
public class WebClientController {

    private static final Log logger = LogFactory.getLog(WebClientController.class);

    @Autowired
    GeoLocationConfig geoLocationConfig;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/geolocation")
    public GeoLocationResponse getGeoLocation(@RequestBody MultiParadigmasProgramozasiNyelvekBeadandoRequest multiParadigmasProgramozasiNyelvekBeadandoRequest) throws MultiParadigmasProgramozasiNyelvekBeadandoRequestIsEmptyException {
        logger.info("getGeoLocation method was called with: " + multiParadigmasProgramozasiNyelvekBeadandoRequest.getAddresses().size() + " parameters");

        if (multiParadigmasProgramozasiNyelvekBeadandoRequest.getAddresses().isEmpty() || (multiParadigmasProgramozasiNyelvekBeadandoRequest.getAddresses().size() == 1 && StringUtils.isEmpty(multiParadigmasProgramozasiNyelvekBeadandoRequest.getAddresses().get(0)))) {
            throw new MultiParadigmasProgramozasiNyelvekBeadandoRequestIsEmptyException("Request was empty");
        }

        WebClient.RequestBodySpec geoLocationUri = getRequestBodySpec(multiParadigmasProgramozasiNyelvekBeadandoRequest);
        WebClient.ResponseSpec response = getResponseSpec(geoLocationUri);

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

}
