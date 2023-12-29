package com.currency.convertor.domain.dto;

import com.currency.convertor.domain.entity.CurrencyData;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
/**
 * The XmlResponse class is a DTO (Data Transfer Object) representing an XML response with the root element "response".
 * It is used for generating XML responses in the application.
 *
 * Properties:
 * - stats: A list of CurrencyData objects representing statistical data.
 * - period: The period associated with the statistical data.
 * - requestId: A unique identifier for the XML response.
 * - consumer: The value of the "consumer" element in the XML response.
 * - currency: The value of the "currency" element in the XML response.
 * - data: An instance of CurrencyData representing specific data associated with the response.
 *
 * This class is annotated with JAXB annotations to specify XML elements and attributes.
 * The XmlRootElement annotation is used to define the root element name.
 * The XmlElement annotation is used to specify the XML element for each property.
 */

@XmlRootElement(name = "response")
public class XmlResponse {
    private List<CurrencyData> stats;
    private Long period;

    @XmlElement
    public List<CurrencyData> getStats() {
        return stats;
    }

    public void setStats(List<CurrencyData> stats) {
        this.stats = stats;
    }

    @XmlElement
    public Long getPeriod() {
        return period;
    }

    public void setPeriod(Long period) {
        this.period = period;
    }

    private String requestId;
    private Long consumer;
    private String currency;
    private CurrencyData data;

    public XmlResponse() {
    }

    public XmlResponse(
            String requestId,
            Long consumer,
            String currency,
            CurrencyData data,
            List<CurrencyData> stats,
            Long period
    ) {
        this.requestId = requestId;
        this.consumer = consumer;
        this.currency = currency;
        this.data = data;
        this.stats = stats;
        this.period = period;
    }

    @XmlElement
    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    @XmlElement
    public Long getConsumer() {
        return consumer;
    }

    public void setConsumer(Long consumer) {
        this.consumer = consumer;
    }

    @XmlElement
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @XmlElement
    public CurrencyData getData() {
        return data;
    }

    public void setData(CurrencyData data) {
        this.data = data;
    }
}
