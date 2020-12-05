package hu.eke.multiparadigmasprogramozasinyelvekbeadando.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Viewport {
    private Location northeast;
    private Location southwest;

    @JsonProperty("northeast")
    public Location getNortheast() { return northeast; }
    @JsonProperty("northeast")
    public void setNortheast(Location value) { this.northeast = value; }

    @JsonProperty("southwest")
    public Location getSouthwest() { return southwest; }
    @JsonProperty("southwest")
    public void setSouthwest(Location value) { this.southwest = value; }
}
