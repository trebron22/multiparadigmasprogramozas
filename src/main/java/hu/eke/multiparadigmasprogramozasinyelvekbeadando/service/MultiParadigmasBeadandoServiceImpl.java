package hu.eke.multiparadigmasprogramozasinyelvekbeadando.service;

import hu.eke.multiparadigmasprogramozasinyelvekbeadando.config.GeoLocationConfig;
import hu.eke.multiparadigmasprogramozasinyelvekbeadando.controller.dto.response.GeoLocationLngLatResponse;
import hu.eke.multiparadigmasprogramozasinyelvekbeadando.controller.dto.response.GeoLocationResponse;
import hu.eke.multiparadigmasprogramozasinyelvekbeadando.dao.AddressEntity;
import hu.eke.multiparadigmasprogramozasinyelvekbeadando.controller.dto.request.City;
import hu.eke.multiparadigmasprogramozasinyelvekbeadando.exception.DoNotExistObjectException;
import hu.eke.multiparadigmasprogramozasinyelvekbeadando.dao.repository.AddressRepository;
import hu.eke.multiparadigmasprogramozasinyelvekbeadando.exception.MultiParadigmasProgramozasiNyelvekBeadandoRequestIsEmptyException;
import hu.eke.multiparadigmasprogramozasinyelvekbeadando.utils.ConvertUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class MultiParadigmasBeadandoServiceImpl implements MultiParadigmasBeadandoService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    GeoLocationConfig geoLocationConfig;

    private static final Log logger = LogFactory.getLog(MultiParadigmasBeadandoServiceImpl.class);

    public void saveSearchParameters(City city) {
        logger.info("saveSearchParameters method was called with: " + city.getName() + " parameters");

        AddressEntity currentAddressEntity = new AddressEntity();
        currentAddressEntity.setSearch(city.getName());
        addressRepository.save(currentAddressEntity);
    }

    public AddressEntity getAddress(Long id) throws DoNotExistObjectException {
        logger.info("getAddress method was called with: " + id + " parameter");

        return addressRepository.findById(id).orElseThrow(() -> new DoNotExistObjectException("Do Not exist object in the database with " + id));
    }

    public AddressEntity editAddress(AddressEntity entity) {
        logger.info("editEmployees method was called with: " + entity.toString() + " parameter");
        return addressRepository.save(entity);
    }

    public void deleteAddress(Long id) {
        logger.info("deleteAddress method was called with: " + id + " parameter");
        addressRepository.deleteById(id);
    }

    public GeoLocationLngLatResponse getGeoLocationResponse(City city) throws MultiParadigmasProgramozasiNyelvekBeadandoRequestIsEmptyException {
        if (city.getName().isEmpty()) {
            throw new MultiParadigmasProgramozasiNyelvekBeadandoRequestIsEmptyException("Request was empty");
        }

        GeoLocationResponse geoLocationResponse = WebClient
                .create()
                .get()
                .uri(geoLocationConfig.getBASE_URL()
                        + geoLocationConfig.getURI()
                        + city.getName()
                        + "&key="
                        + geoLocationConfig.getAPI_KEY())
                .retrieve()
                .bodyToMono(GeoLocationResponse.class)
                .block();
        GeoLocationLngLatResponse geoLocationLngLatResponse = ConvertUtil.convert(geoLocationResponse);

        return geoLocationLngLatResponse;
    }

}
