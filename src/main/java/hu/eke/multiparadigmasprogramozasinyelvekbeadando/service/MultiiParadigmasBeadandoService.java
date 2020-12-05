package hu.eke.multiparadigmasprogramozasinyelvekbeadando.service;

import hu.eke.multiparadigmasprogramozasinyelvekbeadando.config.GeoLocationConfig;
import hu.eke.multiparadigmasprogramozasinyelvekbeadando.controller.dto.response.GeoLocationResponse;
import hu.eke.multiparadigmasprogramozasinyelvekbeadando.dao.Address;
import hu.eke.multiparadigmasprogramozasinyelvekbeadando.dao.GeoLocationSearch;
import hu.eke.multiparadigmasprogramozasinyelvekbeadando.controller.dto.request.MultiParadigmasProgramozasiNyelvekBeadandoRequest;
import hu.eke.multiparadigmasprogramozasinyelvekbeadando.exception.DoNotExistObjectException;
import hu.eke.multiparadigmasprogramozasinyelvekbeadando.dao.repository.AddressRepository;
import hu.eke.multiparadigmasprogramozasinyelvekbeadando.dao.repository.GeoLocationSearchRepository;
import hu.eke.multiparadigmasprogramozasinyelvekbeadando.exception.MultiParadigmasProgramozasiNyelvekBeadandoRequestIsEmptyException;
import hu.eke.multiparadigmasprogramozasinyelvekbeadando.utils.ConvertUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.client.WebClient;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class MultiiParadigmasBeadandoService implements MultiParadigmasBeadandoService{

    @Autowired
    private GeoLocationSearchRepository geoLocationSearchRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    GeoLocationConfig geoLocationConfig;

    private static final Log logger = LogFactory.getLog(MultiiParadigmasBeadandoService.class);

    public void saveSearchParameters(MultiParadigmasProgramozasiNyelvekBeadandoRequest multiParadigmasProgramozasiNyelvekBeadandoRequest) {
        logger.info("saveSearchParameters method was called with: " + multiParadigmasProgramozasiNyelvekBeadandoRequest.getAddresses() + " parameters");
        GeoLocationSearch geoLocationSearch = ConvertUtil.convert(multiParadigmasProgramozasiNyelvekBeadandoRequest);
        geoLocationSearchRepository.save(geoLocationSearch);

        List<Address> addresses = new ArrayList<>();
        for (String address : multiParadigmasProgramozasiNyelvekBeadandoRequest.getAddresses()) {
            Address currentAddress = new Address();
            currentAddress.setSearch(address);
            currentAddress.setGeoLocationSearch(geoLocationSearch);
            addressRepository.save(currentAddress);
        }
    }

    public List<Address> getAddress(Long id) throws DoNotExistObjectException {
        logger.info("getAddress method was called with: " + id + " parameter");

        GeoLocationSearch geoLocationSearch = geoLocationSearchRepository.findById(id).orElseThrow(() -> new DoNotExistObjectException("Do Not exist object in the database with " + id));
        return addressRepository.findByGeoLocationSearch(geoLocationSearch);
    }

    public Address editEmployees(Address entity) {
        logger.info("editEmployees method was called with: " + entity.toString() + " parameter");
        return addressRepository.save(entity);
    }

    public void deleteAddress(Long id) {
        logger.info("deleteAddress method was called with: " + id + " parameter");
        addressRepository.deleteById(id);
    }

    public GeoLocationResponse getGeoLocationResponse(MultiParadigmasProgramozasiNyelvekBeadandoRequest multiParadigmasProgramozasiNyelvekBeadandoRequest) throws MultiParadigmasProgramozasiNyelvekBeadandoRequestIsEmptyException {
        if (multiParadigmasProgramozasiNyelvekBeadandoRequest.getAddresses().isEmpty() || (multiParadigmasProgramozasiNyelvekBeadandoRequest.getAddresses().size() == 1 && StringUtils.isEmpty(multiParadigmasProgramozasiNyelvekBeadandoRequest.getAddresses().get(0)))) {
            throw new MultiParadigmasProgramozasiNyelvekBeadandoRequestIsEmptyException("Request was empty");
        }

        return WebClient
                .create()
                .get()
                .uri(geoLocationConfig.getBASE_URL()
                        +geoLocationConfig.getURI()
                        + String.join(",", multiParadigmasProgramozasiNyelvekBeadandoRequest.getAddresses())
                        + "&key="
                        + geoLocationConfig.getAPI_KEY())
                .retrieve()
                .bodyToMono(GeoLocationResponse.class).block();
    }

}
