package com.currency.convertor.domain.dto;

import com.currency.convertor.domain.entity.CurrencyData;

import java.util.List;
/**
 * The CurrencyStatsHistoryResponse class is a DTO (Data Transfer Object) representing a response for currency statistics history.
 * It includes properties such as request ID, consumer (client), currency, statistics data (list of CurrencyData), and period.
 *
 * Properties:
 * - requestId: A unique identifier for the request associated with the response.
 * - consumer: The client identifier associated with the response.
 * - currency: The currency code for which historical statistics are provided in the response.
 * - stats: A list of CurrencyData objects containing historical currency statistics.
 * - period: The period in hours for which historical statistics are provided.
 *
 * This class is designed to facilitate the transfer of data related to currency statistics history responses within the application.
 * Getter and setter methods are provided for each property.
 */

public class CurrencyStatsHistoryResponse {

    private List<CurrencyData> stats;
    private Long period;
    private String requestId;
    private Long consumer;
    private String currency;
    public CurrencyStatsHistoryResponse(String requestId, Long consumer, String currency, List<CurrencyData> stats, Long period) {
        this.requestId = requestId;
        this.consumer = consumer;
        this.currency = currency;
        this.stats = stats;
        this.period = period;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Long getConsumer() {
        return consumer;
    }

    public void setConsumer(Long consumer) {
        this.consumer = consumer;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public List<CurrencyData> getStats() {
        return stats;
    }

    public void setStats(List<CurrencyData> stats) {
        this.stats = stats;
    }

    public Long getPeriod() {
        return period;
    }

    public void setPeriod(Long period) {
        this.period = period;
    }
}
