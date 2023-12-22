package com.currency.convertor.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonResponse {
    private String requestId;
    private String consumer;
    private String currency;
    private double data;

    public JsonResponse() {
        // Default constructor for Jackson
    }

    public JsonResponse(String requestId, String consumer, String currency, double data) {
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
    public double getData() {
        return data;
    }

    public void setData(double data) {
        this.data = data;
    }
}
