package com.currency.convertor.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * The CurrencyStasRequest class is a DTO (Data Transfer Object) representing a request for currency statistics.
 * It includes properties such as request ID, timestamp, client, currency, and period.
 *
 * Properties:
 * - requestId: A unique identifier for the request.
 * - timestamp: The timestamp associated with the request.
 * - client: The client identifier associated with the request.
 * - currency: The currency code for which statistics are requested.
 * - period: The period (in hours) for which historical statistics are requested.
 *
 * This class is designed to facilitate the transfer of data related to currency statistics requests within the application.
 * The @JsonProperty annotations are used to specify the names of the JSON properties when serialized or deserialized.
 * Getter and setter methods are provided for each property.
 */
public class CurrencyStasRequest {
    private String requestId;
    private Long timestamp;
    private Long client;
    private String currency;
    private Long period;

    public CurrencyStasRequest() {
    }

    @JsonProperty("requestId")
    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    @JsonProperty("timestamp")
    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @JsonProperty("client")
    public Long getClient() {
        return client;
    }

    public void setClient(Long client) {
        this.client = client;
    }

    @JsonProperty("currency")
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @JsonProperty("period")
    public Long getPeriod() {
        return period;
    }

    public void setPeriod(Long period) {
        this.period = period;
    }

}
