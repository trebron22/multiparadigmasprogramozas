package hu.eke.multiparadigmasprogramozasinyelvekbeadando.utils;

import hu.eke.multiparadigmasprogramozasinyelvekbeadando.dao.Address;
import hu.eke.multiparadigmasprogramozasinyelvekbeadando.dao.GeoLocationSearch;
import hu.eke.multiparadigmasprogramozasinyelvekbeadando.controller.dto.request.MultiParadigmasProgramozasiNyelvekBeadandoRequest;

import java.util.ArrayList;
import java.util.List;

public class ConvertUtil {
    public static GeoLocationSearch convert(MultiParadigmasProgramozasiNyelvekBeadandoRequest multiParadigmasProgramozasiNyelvekBeadandoRequest) {
        GeoLocationSearch geoLocationSearch = new GeoLocationSearch();
        List<Address> addresses = new ArrayList<>();
        for (String address: multiParadigmasProgramozasiNyelvekBeadandoRequest.getAddresses()) {
            Address currentAddress = new Address();
            currentAddress.setSearch(address);
        }
        geoLocationSearch.setAddresses(addresses);
        return  geoLocationSearch;
    }
}
