package com.currency.convertor.domain.dto;

import com.currency.convertor.domain.entity.CurrencyData;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CurrencyStasResponse {
    private String requestId;
    private String consumer;
    private String currency;
    private CurrencyData data;

    public CurrencyStasResponse() {
        // Default constructor for Jackson
    }

    public CurrencyStasResponse(String requestId, String consumer, String currency, CurrencyData data) {
        this.requestId = requestId;
        this.consumer = consumer;
        this.currency = currency;
        this.data = data;
    }

    @JsonProperty("requestId")
    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    @JsonProperty("consumer")
    public String getConsumer() {
        return consumer;
    }

    public void setConsumer(String consumer) {
        this.consumer = consumer;
    }

    @JsonProperty("currency")
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @JsonProperty("data")
    public CurrencyData getData() {
        return data;
    }

    public void setData(CurrencyData data) {
        this.data = data;
    }
}
