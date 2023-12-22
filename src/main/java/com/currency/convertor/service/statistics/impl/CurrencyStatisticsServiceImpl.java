package com.currency.convertor.service.statistics.impl;

import com.currency.convertor.domain.dto.*;
import com.currency.convertor.domain.entity.CurrencyData;
import com.currency.convertor.domain.entity.RequestDetails;
import com.currency.convertor.exception.DuplicateRequestIdException;
import com.currency.convertor.repository.CurrencyDataRepository;
import com.currency.convertor.repository.RequestDetailsRepository;
import com.currency.convertor.service.statistics.CurrencyStatisticsService;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Service
class CurrencyStatisticsServiceImpl implements CurrencyStatisticsService {
    private final Set<String> previousRequestIds = new HashSet<>();
    private final RequestDetailsRepository requestDetailsRepository;
    private final CurrencyDataRepository currencyDataRepository;

    CurrencyStatisticsServiceImpl(RequestDetailsRepository requestDetailsRepository, CurrencyDataRepository currencyDataRepository) {
        this.requestDetailsRepository = requestDetailsRepository;
        this.currencyDataRepository = currencyDataRepository;
    }

    @Override
    public CurrencyStasResponse processJsonCurrentRequest(CurrencyStasRequest jsonRequest) throws DuplicateRequestIdException {
        String requestId = jsonRequest.getRequestId();
        String client = jsonRequest.getClient();
        String currency = jsonRequest.getCurrency();

        if (isDuplicateRequest(requestId)) {
            throw new DuplicateRequestIdException();
        }

        CurrencyData data = fetchRecordForCurrency(currency);

        saveRequestDetails(requestId, client, currency);

        return new CurrencyStasResponse(requestId, client, currency, data);
    }

    private CurrencyData fetchRecordForCurrency(String currency) {
        return currencyDataRepository.findFirstByCurrencyOrderByTimestampDesc(currency);
    }

    @Override
    public XmlResponse processXmlCommand(XmlRequest xmlRequest) {
        //todo: implement this
        throw new NotYetImplementedException();
    }

    @Override
    public CurrencyStatsHistoryResponse processJsonHistoryRequest(CurrencyStasRequest jsonRequest) throws DuplicateRequestIdException  {
        String requestId = jsonRequest.getRequestId();

        if (isDuplicateRequest(requestId)) {
            throw new DuplicateRequestIdException();
        }

        String client = jsonRequest.getClient();
        String currency = jsonRequest.getCurrency();
        Long unixTimeInSeconds = jsonRequest.getTimestamp();
        Long periodInHours = jsonRequest.getPeriod();

        var result = currencyDataRepository.findAllByCurrencyAndTimestampBetween(currency, (unixTimeInSeconds - periodInHours * 60 * 60), unixTimeInSeconds);

        return new CurrencyStatsHistoryResponse(requestId, client, currency, result, periodInHours);
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