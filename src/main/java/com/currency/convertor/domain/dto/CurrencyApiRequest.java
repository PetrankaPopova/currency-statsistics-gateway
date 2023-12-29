package com.currency.convertor.domain.dto;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
/**
 * The CurrencyApiRequest class is a DTO (Data Transfer Object) representing a request for currency information
 * from an external API. It contains properties such as currency code, rates, and date.
 *
 * Properties:
 * - currency: The code of the currency for which information is requested.
 * - rates: A map containing currency codes as keys and their corresponding exchange rates as values.
 * - date: The date of the currency information.
 *
 * This class is designed to facilitate the transfer of data related to currency information within the application.
 * The rates property is initialized as an empty HashMap in the constructor, and there are getter and setter methods
 * for each property. Additionally, there is a isSuccess() method that always returns true, indicating a successful request.
 */
public class CurrencyApiRequest {
    private String currency;
    private Map<String, Double> rates;
    private LocalDateTime date;


    public CurrencyApiRequest() {
        this.rates = new HashMap<>();
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }


    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public boolean isSuccess() {
        return true;
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }
}
