package hu.eke.multiparadigmasprogramozasinyelvekbeadando.utils;

import hu.eke.multiparadigmasprogramozasinyelvekbeadando.controller.dto.response.GeoLocationLngLatResponse;
import hu.eke.multiparadigmasprogramozasinyelvekbeadando.controller.dto.response.GeoLocationResponse;

public class ConvertUtil {

    public static GeoLocationLngLatResponse convert(GeoLocationResponse geoLocationResponse) {
        GeoLocationLngLatResponse geoLocationLngLatResponse = new GeoLocationLngLatResponse();

        geoLocationLngLatResponse.setLat(geoLocationResponse.getResults()[0].getGeometry().getLocation().getLat());
        geoLocationLngLatResponse.setLat(geoLocationResponse.getResults()[0].getGeometry().getLocation().getLng());
        return geoLocationLngLatResponse;
    }


}
