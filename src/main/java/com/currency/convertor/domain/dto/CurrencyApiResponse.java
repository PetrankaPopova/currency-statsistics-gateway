package com.currency.convertor.domain.dto;

import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
/**
 * The CurrencyApiResponse class is a DTO (Data Transfer Object) representing a response from an external API
 * providing currency information. It contains properties such as currency code, success status, rates, date, and timestamp.
 *
 * Properties:
 * - currency: The code of the currency for which information is provided.
 * - success: A boolean indicating the success status of the API response.
 * - rates: A map containing currency codes as keys and their corresponding exchange rates as values.
 * - date: The date associated with the currency information.
 * - timestamp: A timestamp indicating the time when the API response was received.
 *
 * This class is designed to facilitate the transfer of data related to currency information within the application.
 * The rates property is initialized as an empty HashMap in the constructor. There are getter and setter methods for
 * each property, and a DateTimeFormat annotation is used to specify the date format. The isSuccess() method is provided
 * to check the success status of the API response.
 */
public class CurrencyApiResponse implements Serializable {
    private String currency;
    private boolean success;
    private Map<String, Double> rates;
    private Date date;
    private Long timestamp;

    public CurrencyApiResponse() {
        this.rates = new HashMap<>();
    }

    public CurrencyApiResponse(String currency, Double aDouble) {
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
