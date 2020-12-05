package hu.eke.multiparadigmasprogramozasinyelvekbeadando.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Location {

    private double lat;
    private double lng;

    @JsonProperty("lat")
    public double getLat() { return lat; }
    @JsonProperty("lat")
    public void setLat(double value) { this.lat = value; }

    @JsonProperty("lng")
    public double getLng() { return lng; }
    @JsonProperty("lng")
    public void setLng(double value) { this.lng = value; }
}
