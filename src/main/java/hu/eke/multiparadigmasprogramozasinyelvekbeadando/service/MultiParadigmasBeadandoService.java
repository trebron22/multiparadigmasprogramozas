package hu.eke.multiparadigmasprogramozasinyelvekbeadando.service;

import hu.eke.multiparadigmasprogramozasinyelvekbeadando.controller.dto.request.MultiParadigmasProgramozasiNyelvekBeadandoRequest;
import hu.eke.multiparadigmasprogramozasinyelvekbeadando.controller.dto.response.GeoLocationResponse;
import hu.eke.multiparadigmasprogramozasinyelvekbeadando.dao.Address;
import hu.eke.multiparadigmasprogramozasinyelvekbeadando.exception.DoNotExistObjectException;
import hu.eke.multiparadigmasprogramozasinyelvekbeadando.exception.MultiParadigmasProgramozasiNyelvekBeadandoRequestIsEmptyException;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

public interface MultiParadigmasBeadandoService {

    void saveSearchParameters(MultiParadigmasProgramozasiNyelvekBeadandoRequest multiParadigmasProgramozasiNyelvekBeadandoRequest);

    List<Address> getAddress(Long id) throws DoNotExistObjectException;

    Address editEmployees(Address entity);

    void deleteAddress(Long id);

    GeoLocationResponse getGeoLocationResponse(MultiParadigmasProgramozasiNyelvekBeadandoRequest multiParadigmasProgramozasiNyelvekBeadandoRequest) throws MultiParadigmasProgramozasiNyelvekBeadandoRequestIsEmptyException;

}
