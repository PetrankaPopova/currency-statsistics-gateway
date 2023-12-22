package com.currency.convertor.service.currency.impl;

import com.currency.convertor.client.FixerIoApiClient;
import com.currency.convertor.domain.dto.CurrencyApiRequest;
import com.currency.convertor.domain.dto.CurrencyApiResponse;
import com.currency.convertor.domain.entity.CurrencyData;
import com.currency.convertor.repository.CurrencyDataRepository;
import com.currency.convertor.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    private final FixerIoApiClient apiClient;
    private final CurrencyDataRepository currencyDataRepository;

    @Autowired
    public CurrencyServiceImpl(FixerIoApiClient apiClient, CurrencyDataRepository currencyDataRepository) {
        this.apiClient = apiClient;
        this.currencyDataRepository = currencyDataRepository;
    }

    @Override
    public String fetchDataAndStoreInDatabase() {

        CurrencyApiResponse response = apiClient.getCurrencyApiResponse();
        var date = response.getDate();

        if (response != null && response.isSuccess()) {
            response.getRates().forEach((key, value) -> {

                CurrencyData currencyData = new CurrencyData();
                currencyData.setCurrency(key);
                currencyData.setRate(value);
                currencyData.setDate(date);

                currencyDataRepository.save(currencyData);
            });
        }
        return apiClient.getApiUrl();
    }

    @Override
    public CurrencyApiResponse processExternalService1(CurrencyApiRequest request) {
        CurrencyApiResponse response = apiClient.getCurrencyApiResponse();
        return new CurrencyApiResponse(request.getCurrency(), response.getRates().get(request.getCurrency()));
    }

    @Override
    public CurrencyApiResponse processExternalService2(CurrencyApiRequest request) {
        CurrencyApiResponse response = apiClient.getCurrencyApiResponse();
        return new CurrencyApiResponse(request.getCurrency(), response.getRates().get(request.getCurrency()) * 2.0);
    }
}
