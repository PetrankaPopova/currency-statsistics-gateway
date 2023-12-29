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
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
/**
 * The CurrencyStatisticsServiceImpl class provides the implementation for the CurrencyStatisticsService interface.
 * It handles processing requests related to currency statistics, including handling duplicate request IDs, fetching
 * currency data, and interacting with external systems via RabbitMQ.
 *
 * Key Features:
 * - Handles requests for current and historical currency statistics.
 * - Ensures uniqueness of request IDs to prevent duplicate processing.
 * - Utilizes RabbitMQSender to send messages related to request history.
 * - Implements scheduled tasks using Spring's @Scheduled annotation for periodic operations.
 *
 * Methods:
 * - processCurrencyStatsRequest: Processes a request for current currency statistics, handling duplicate request IDs
 *   and sending the response to RabbitMQ for request history.
 * - processCurrencyHistoryRequest: Processes a request for historical currency statistics within a specified period.
 * - isDuplicateRequest: Checks if the provided request ID is a duplicate, preventing duplicate processing.
 * - saveRequestDetails: Saves details of the processed request to the repository.
 * - fetchRecordForCurrency: Fetches the latest currency data record for a given currency.
 * - serializeToJson: Serializes a CurrencyStasResponse object to JSON format.
 *
 * Usage Example:
 * - Used by controllers to handle incoming requests for currency statistics processing.
 * - Collaborates with RabbitMQSender, RequestDetailsRepository, and CurrencyDataRepository.
 * - Ensures data integrity and consistency by preventing duplicate requests.
 */

@Service
public class CurrencyStatisticsServiceImpl implements CurrencyStatisticsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyStatisticsServiceImpl.class);
    private final RabbitMQSender mqClient;
    private final Set<String> previousRequestIds = new HashSet<>();
    private final RequestDetailsRepository requestDetailsRepository;
    private final CurrencyDataRepository currencyDataRepository;

    public CurrencyStatisticsServiceImpl(RabbitMQSender mqClient, RequestDetailsRepository requestDetailsRepository, CurrencyDataRepository currencyDataRepository) {
        this.mqClient = mqClient;
        this.requestDetailsRepository = requestDetailsRepository;
        this.currencyDataRepository = currencyDataRepository;
    }

    @Override
    public CurrencyStasResponse processCurrencyStatsRequest(CurrencyStasRequest jsonRequest) throws DuplicateRequestIdException {
        String requestId = jsonRequest.getRequestId();
        Long client = jsonRequest.getClient();
        String currency = jsonRequest.getCurrency();

      //  LOGGER.info("step1");

        if (isDuplicateRequest(requestId)) {
            throw new DuplicateRequestIdException();}

        CurrencyData data = fetchRecordForCurrency(currency);

        saveRequestDetails(requestId, client, currency);
        CurrencyStasResponse response = new CurrencyStasResponse(requestId, client, currency, data);

        String serializedResponse = serializeToJson(response);
        mqClient.sendMessageToRequestHistory(serializedResponse);
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

    public synchronized boolean isDuplicateRequest(String requestId) {

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