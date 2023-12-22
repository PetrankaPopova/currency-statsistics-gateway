package com.currency.convertor.domain.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "response")
public class XmlResponse {
    private String requestId;
    private String consumer;
    private String currency;
    private double data;

    public XmlResponse() {
        // Default constructor for JAXB
    }

    public XmlResponse(String requestId, String consumer, String currency, double data) {
        this.requestId = requestId;
        this.consumer = consumer;
        this.currency = currency;
        this.data = data;
    }

    @XmlElement
    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    @XmlElement
    public String getConsumer() {
        return consumer;
    }

    public void setConsumer(String consumer) {
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
    public double getData() {
        return data;
    }

    public void setData(double data) {
        this.data = data;
    }
}
