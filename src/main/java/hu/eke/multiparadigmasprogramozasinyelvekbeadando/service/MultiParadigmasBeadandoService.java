package hu.eke.multiparadigmasprogramozasinyelvekbeadando.service;

import hu.eke.multiparadigmasprogramozasinyelvekbeadando.controller.dto.request.City;
import hu.eke.multiparadigmasprogramozasinyelvekbeadando.controller.dto.response.GeoLocationLngLatResponse;
import hu.eke.multiparadigmasprogramozasinyelvekbeadando.dao.AddressEntity;
import hu.eke.multiparadigmasprogramozasinyelvekbeadando.exception.DoNotExistObjectException;
import hu.eke.multiparadigmasprogramozasinyelvekbeadando.exception.MultiParadigmasProgramozasiNyelvekBeadandoRequestIsEmptyException;

public interface MultiParadigmasBeadandoService {

  //  void saveSearchParameters(City multiParadigmasProgramozasiNyelvekBeadandoRequest);

    AddressEntity getAddress(Long id) throws DoNotExistObjectException;

    AddressEntity editAddress(AddressEntity entity);

    void deleteAddress(Long id);

    GeoLocationLngLatResponse getGeoLocationResponse(City multiParadigmasProgramozasiNyelvekBeadandoRequest) throws MultiParadigmasProgramozasiNyelvekBeadandoRequestIsEmptyException;

}
