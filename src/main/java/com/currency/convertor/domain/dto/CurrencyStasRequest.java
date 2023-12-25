package com.currency.convertor.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

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
