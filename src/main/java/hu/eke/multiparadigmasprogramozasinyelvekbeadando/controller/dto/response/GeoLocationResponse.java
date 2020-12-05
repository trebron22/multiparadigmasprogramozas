package hu.eke.multiparadigmasprogramozasinyelvekbeadando.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import hu.eke.multiparadigmasprogramozasinyelvekbeadando.dto.Result;

public class GeoLocationResponse {
    private Result[] results;
    private String status;

    @JsonProperty("results")
    public Result[] getResults() { return results; }
    @JsonProperty("results")
    public void setResults(Result[] value) { this.results = value; }

    @JsonProperty("status")
    public String getStatus() { return status; }
    @JsonProperty("status")
    public void setStatus(String value) { this.status = value; }
}