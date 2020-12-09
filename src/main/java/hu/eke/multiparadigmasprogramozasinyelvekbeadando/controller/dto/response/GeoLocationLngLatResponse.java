package hu.eke.multiparadigmasprogramozasinyelvekbeadando.controller.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class GeoLocationLngLatResponse {

    private double lat;
    private double lng;
}
