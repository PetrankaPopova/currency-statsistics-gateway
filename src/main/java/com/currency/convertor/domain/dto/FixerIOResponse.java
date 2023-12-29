package com.currency.convertor.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;
/**
 * The FixerIOResponse class is a DTO (Data Transfer Object) representing the response from the Fixer.io API.
 * It includes properties such as success, timestamp, base currency, and exchange rates for various currencies.
 *
 * Properties:
 * - success: A boolean indicating whether the API request was successful.
 * - timestamp: The timestamp of the API response.
 * - base: The base currency against which exchange rates are provided.
 * - rates: A map containing exchange rates for different currencies.
 *
 * This class is designed to deserialize the JSON response from the Fixer.io API and facilitate the transfer of relevant data within the application.
 * Getter and setter methods are provided for each property, and the class is annotated with @JsonIgnoreProperties(ignoreUnknown = true) to ignore unknown properties during deserialization.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FixerIOResponse {
    private boolean success;
    private long timestamp;
    private String base;
    private Map<String, Double> rates;

    public FixerIOResponse() {
    }

    @JsonProperty("success")
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @JsonProperty("timestamp")
    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @JsonProperty("base")
    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    @JsonProperty("rates")
    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }

}
