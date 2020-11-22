package hu.eke.multiparadigmasprogramozasinyelvekbeadando.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Result {
    private AddressComponent[] addressComponents;
    private String formattedAddress;
    private Geometry geometry;
    private String placeID;
    private PlusCode plusCode;
    private String[] types;

    @JsonProperty("address_components")
    public AddressComponent[] getAddressComponents() { return addressComponents; }
    @JsonProperty("address_components")
    public void setAddressComponents(AddressComponent[] value) { this.addressComponents = value; }

    @JsonProperty("formatted_address")
    public String getFormattedAddress() { return formattedAddress; }
    @JsonProperty("formatted_address")
    public void setFormattedAddress(String value) { this.formattedAddress = value; }

    @JsonProperty("geometry")
    public Geometry getGeometry() { return geometry; }
    @JsonProperty("geometry")
    public void setGeometry(Geometry value) { this.geometry = value; }

    @JsonProperty("place_id")
    public String getPlaceID() { return placeID; }
    @JsonProperty("place_id")
    public void setPlaceID(String value) { this.placeID = value; }

    @JsonProperty("plus_code")
    public PlusCode getPlusCode() { return plusCode; }
    @JsonProperty("plus_code")
    public void setPlusCode(PlusCode value) { this.plusCode = value; }

    @JsonProperty("types")
    public String[] getTypes() { return types; }
    @JsonProperty("types")
    public void setTypes(String[] value) { this.types = value; }
}

