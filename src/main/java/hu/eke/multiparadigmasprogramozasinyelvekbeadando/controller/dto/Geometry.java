package hu.eke.multiparadigmasprogramozasinyelvekbeadando.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Geometry {
    private Location location;
    private String locationType;
    private Viewport viewport;

    @JsonProperty("location")
    public Location getLocation() { return location; }
    @JsonProperty("location")
    public void setLocation(Location value) { this.location = value; }

    @JsonProperty("location_type")
    public String getLocationType() { return locationType; }
    @JsonProperty("location_type")
    public void setLocationType(String value) { this.locationType = value; }

    @JsonProperty("viewport")
    public Viewport getViewport() { return viewport; }
    @JsonProperty("viewport")
    public void setViewport(Viewport value) { this.viewport = value; }
}
