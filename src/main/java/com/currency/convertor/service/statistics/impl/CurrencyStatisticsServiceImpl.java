package com.currency.convertor.service.statistics.impl;

import com.currency.convertor.client.RabbitMQSender;
import com.currency.convertor.domain.dto.CurrencyStasRequest;
import com.currency.convertor.domain.dto.CurrencyStasResponse;
import com.currency.convertor.domain.dto.CurrencyStatsHistoryResponse;
import com.currency.convertor.domain.entity.CurrencyData;
import com.currency.convertor.domain.entity.RequestDetails;
import com.currency.convertor.exception.DuplicateRequestIdException;
import com.currency.convertor.repository.CurrencyDataRepository;
import com.currency.convertor.repository.RequestDetailsRepository;
import com.currency.convertor.service.statistics.CurrencyStatisticsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Service
class CurrencyStatisticsServiceImpl implements CurrencyStatisticsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyStatisticsServiceImpl.class);
    private final RabbitMQSender mqClient;
    private final Set<String> previousRequestIds = new HashSet<>();
    private final RequestDetailsRepository requestDetailsRepository;
    private final CurrencyDataRepository currencyDataRepository;

    CurrencyStatisticsServiceImpl(RabbitMQSender mqClient, RequestDetailsRepository requestDetailsRepository, CurrencyDataRepository currencyDataRepository) {
        this.mqClient = mqClient;
        this.requestDetailsRepository = requestDetailsRepository;
        this.currencyDataRepository = currencyDataRepository;
    }

    @Override
    public CurrencyStasResponse processCurrencyStatsRequest(CurrencyStasRequest jsonRequest) throws DuplicateRequestIdException {
        String requestId = jsonRequest.getRequestId();
        Long client = jsonRequest.getClient();
        String currency = jsonRequest.getCurrency();

        LOGGER.info("step1");

        if (isDuplicateRequest(requestId)) {
            throw new DuplicateRequestIdException();
        }

        CurrencyData data = fetchRecordForCurrency(currency);

        saveRequestDetails(requestId, client, currency);
        CurrencyStasResponse response = new CurrencyStasResponse(requestId, client, currency, data);

        String serializedResponse = serializeToJson(response);
        mqClient.sendMessageToRequestHistory(serializedResponse);
        // return new CurrencyStasResponse(requestId, client, currency, data);
        return response;
    }

    private String serializeToJson(CurrencyStasResponse obj) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("Error serializing object to JSON", e);
        }
    }

    private CurrencyData fetchRecordForCurrency(String currency) {
        return currencyDataRepository.findFirstByCurrencyOrderByTimestampDesc(currency);
    }

    @Override
    public CurrencyStatsHistoryResponse processCurrencyHistoryRequest(CurrencyStasRequest jsonRequest) throws DuplicateRequestIdException {
        String requestId = jsonRequest.getRequestId();

        if (isDuplicateRequest(requestId)) {
            throw new DuplicateRequestIdException();
        }

        Long client = jsonRequest.getClient();
        String currency = jsonRequest.getCurrency();
        Long unixTimeInSeconds = jsonRequest.getTimestamp();
        Long periodInHours = jsonRequest.getPeriod();

        var result = currencyDataRepository.findAllByCurrencyAndTimestampBetween(currency, (unixTimeInSeconds - periodInHours * 60 * 60), unixTimeInSeconds);

        return new CurrencyStatsHistoryResponse(requestId, client, currency, result, periodInHours);
    }

    synchronized private boolean isDuplicateRequest(String requestId) {

        if (previousRequestIds.contains(requestId)) {
            return true;
        } else {
            previousRequestIds.add(requestId);
            return false;
        }
    }

    private void saveRequestDetails(String requestId, Long client, String currency) {
        RequestDetails requestDetails = new RequestDetails();
        requestDetails.setRequestId(requestId);
        requestDetails.setClient(client);
        requestDetails.setCurrency(currency);
        requestDetails.setTimestamp(LocalDateTime.now());

        requestDetailsRepository.save(requestDetails);
    }

}