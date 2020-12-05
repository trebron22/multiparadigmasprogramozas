package hu.eke.multiparadigmasprogramozasinyelvekbeadando.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddressComponent {
    private String longName;
    private String shortName;
    private String[] types;

    @JsonProperty("long_name")
    public String getLongName() { return longName; }
    @JsonProperty("long_name")
    public void setLongName(String value) { this.longName = value; }

    @JsonProperty("short_name")
    public String getShortName() { return shortName; }
    @JsonProperty("short_name")
    public void setShortName(String value) { this.shortName = value; }

    @JsonProperty("types")
    public String[] getTypes() { return types; }
    @JsonProperty("types")
    public void setTypes(String[] value) { this.types = value; }
}
