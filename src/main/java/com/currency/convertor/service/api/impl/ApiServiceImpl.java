package com.currency.convertor.service.api.impl;

import com.currency.convertor.domain.dto.*;
import com.currency.convertor.domain.entity.RequestDetails;
import com.currency.convertor.repository.RequestDetailsRepository;
import com.currency.convertor.service.api.ApiService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Service
class ApiServiceImpl implements ApiService {
    private RequestDetailsRepository requestDetailsRepository;
    private final RestTemplate restTemplate;
    private final Set<String> previousRequestIds = new HashSet<>();

    ApiServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public CurrencyStasResponse processJsonCurrentRequest(CurrencyStasRequest jsonRequest) {
        String requestId = jsonRequest.getRequestId();
        String client = jsonRequest.getClient();
        String currency = jsonRequest.getCurrency();


        if (isDuplicateRequest(requestId)) {
            return new CurrencyStasResponse(requestId, client, currency, 0.0);
        }

        double data = fetchDataFromFixerIO(currency);
        saveRequestDetails(requestId, client, currency);
        return new CurrencyStasResponse(requestId, client, currency, data);
    }

    private double fetchDataFromFixerIO(String currency) {
        String fixerApiUrl = null;
        String apiUrl = fixerApiUrl + "?access_key=" + "fixerApiKey" + "&base=USD&symbols=" + currency;

        try {
            FixerIOResponse fixerIOResponse = restTemplate.getForObject(apiUrl, FixerIOResponse.class);
            return fixerIOResponse.getRates().get(currency);
        } catch (Exception e) {
            throw new RuntimeException("Error fetching data from Fixer.io", e);
        }
    }

    @Override
    public XmlResponse processXmlCommand(XmlRequest xmlRequest) {
        return null;
    }

    @Override
    public CurrencyStasResponse processJsonHistoryRequest(CurrencyStasRequest jsonRequest) {
        String requestId = jsonRequest.getRequestId();
        String client = jsonRequest.getClient();
        String currency = jsonRequest.getCurrency();
        int period = jsonRequest.getPeriod();
        return new CurrencyStasResponse(requestId, client, currency, period);
    }

    private boolean isDuplicateRequest(String requestId) {

            if (previousRequestIds.contains(requestId)) {
                return true;
            } else {
                previousRequestIds.add(requestId);
                return false;
            }
    }

    private void saveRequestDetails(String requestId, String client, String currency) {
        RequestDetails requestDetails = new RequestDetails();
        requestDetails.setRequestId(requestId);
        requestDetails.setClient(client);
        requestDetails.setCurrency(currency);
        requestDetails.setTimestamp(LocalDateTime.now());


        requestDetailsRepository.save(requestDetails);
}
    }

 /*   @Override
    public XmlResponse processXmlCommand(XmlRequest xmlRequest) {
        return null;
    }*/

