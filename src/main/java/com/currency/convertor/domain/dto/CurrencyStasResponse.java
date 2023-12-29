package com.currency.convertor.domain.dto;

import com.currency.convertor.domain.entity.CurrencyData;
import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * The CurrencyStasResponse class is a DTO (Data Transfer Object) representing a response for currency statistics.
 * It includes properties such as request ID, consumer (client), currency, and data (CurrencyData).
 *
 * Properties:
 * - requestId: A unique identifier for the request associated with the response.
 * - consumer: The client identifier associated with the response.
 * - currency: The currency code for which statistics are provided in the response.
 * - data: An instance of CurrencyData containing detailed currency statistics.
 *
 * This class is designed to facilitate the transfer of data related to currency statistics responses within the application.
 * The @JsonProperty annotations are used to specify the names of the JSON properties when serialized or deserialized.
 * Getter and setter methods are provided for each property.
 */

public class CurrencyStasResponse {
    private String requestId;
    private Long consumer;
    private String currency;
    private CurrencyData data;



    public CurrencyStasResponse(String requestId, Long consumer, String currency, CurrencyData data) {
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
    public Long getConsumer() {
        return consumer;
    }

    public void setConsumer(Long consumer) {
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
