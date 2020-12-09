package hu.eke.multiparadigmasprogramozasinyelvekbeadando.controller;

import hu.eke.multiparadigmasprogramozasinyelvekbeadando.config.GeoLocationConfig;
import hu.eke.multiparadigmasprogramozasinyelvekbeadando.controller.dto.response.GeoLocationLngLatResponse;
import hu.eke.multiparadigmasprogramozasinyelvekbeadando.dao.AddressEntity;
import hu.eke.multiparadigmasprogramozasinyelvekbeadando.controller.dto.request.City;
import hu.eke.multiparadigmasprogramozasinyelvekbeadando.exception.DoNotExistObjectException;
import hu.eke.multiparadigmasprogramozasinyelvekbeadando.exception.MultiParadigmasProgramozasiNyelvekBeadandoRequestIsEmptyException;
import hu.eke.multiparadigmasprogramozasinyelvekbeadando.controller.dto.Result;
import hu.eke.multiparadigmasprogramozasinyelvekbeadando.controller.dto.response.GeoLocationResponse;
import hu.eke.multiparadigmasprogramozasinyelvekbeadando.service.MultiParadigmasBeadandoServiceImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class WebClientController {

    private static final Log logger = LogFactory.getLog(WebClientController.class);

    @Autowired
    GeoLocationConfig geoLocationConfig;

    @Autowired
    MultiParadigmasBeadandoServiceImpl multiParadigmasBeadandoService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/geolocation/{addressId}")
    public AddressEntity getAddress(@PathVariable Long addressId) throws DoNotExistObjectException {
        logger.info("getGeoLocationSearch method was called with: " + addressId + " parameter");
        return multiParadigmasBeadandoService.getAddress(addressId);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/address")
    public ResponseEntity<AddressEntity> updateAddress(@RequestBody AddressEntity addressEntity) {
        logger.info("updateAddress method was called with: " + addressEntity + " parameter");
        AddressEntity editAddressEntity = multiParadigmasBeadandoService.editAddress(addressEntity);
        return new ResponseEntity<>(editAddressEntity, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)

    @DeleteMapping("/address/{addressId}")
    public ResponseEntity<String> deleteAddress(@PathVariable Long addressId) {
        logger.info("deleteAddress method was called with: " + addressId + " parameter");
        multiParadigmasBeadandoService.deleteAddress(addressId);
        return new ResponseEntity<>("Address with ID :" + addressId + " deleted successfully", HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/geolocation")
    public GeoLocationLngLatResponse getGeoLocation(@RequestBody City city) throws MultiParadigmasProgramozasiNyelvekBeadandoRequestIsEmptyException {
        logger.info("getGeoLocation method was called with: " + city.getName() + " parameters");

        GeoLocationLngLatResponse geoLocationLngLatResponse = multiParadigmasBeadandoService.getGeoLocationResponse(city);

        if (geoLocationLngLatResponse != null) {
            multiParadigmasBeadandoService.saveSearchParameters(city);
        }

        return geoLocationLngLatResponse;
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
    public AddressEntity doNotExistObjectException(DoNotExistObjectException exception) {
        logger.info("doNotExistObjectException method was called with: " + exception.getMessage() + " message");
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setSearch("");
        return addressEntity;
    }

    @ExceptionHandler(value = EmptyResultDataAccessException.class)
    public AddressEntity emptyResultDataAccessException(EmptyResultDataAccessException exception) {
        logger.info("emptyResultDataAccessException method was called with: " + exception.getMessage() + " message");
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setSearch("");
        return addressEntity;
    }

    @ExceptionHandler(value = NumberFormatException.class)
    public AddressEntity numberFormatException(NumberFormatException exception) {
        logger.info("numberFormatException method was called with: " + exception.getMessage() + " message");
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setSearch("");
        return addressEntity;
    }

}
