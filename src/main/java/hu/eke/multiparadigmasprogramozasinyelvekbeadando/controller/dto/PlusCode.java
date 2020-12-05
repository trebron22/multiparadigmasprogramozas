package hu.eke.multiparadigmasprogramozasinyelvekbeadando.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PlusCode {
    private String compoundCode;
    private String globalCode;

    @JsonProperty("compound_code")
    public String getCompoundCode() { return compoundCode; }
    @JsonProperty("compound_code")
    public void setCompoundCode(String value) { this.compoundCode = value; }

    @JsonProperty("global_code")
    public String getGlobalCode() { return globalCode; }
    @JsonProperty("global_code")
    public void setGlobalCode(String value) { this.globalCode = value; }
}
