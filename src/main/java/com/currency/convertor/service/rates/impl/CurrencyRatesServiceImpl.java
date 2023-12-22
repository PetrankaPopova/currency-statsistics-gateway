package com.currency.convertor.service.rates.impl;

import com.currency.convertor.client.FixerIoApiClient;
import com.currency.convertor.client.RabbitMQSender;
import com.currency.convertor.domain.dto.CurrencyApiRequest;
import com.currency.convertor.domain.dto.CurrencyApiResponse;
import com.currency.convertor.domain.entity.CurrencyData;
import com.currency.convertor.repository.CurrencyDataRepository;
import com.currency.convertor.service.rates.CurrencyRatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class CurrencyRatesServiceImpl implements CurrencyRatesService {

    private final FixerIoApiClient apiClient;

    private final RabbitMQSender mqClient;
    private final CurrencyDataRepository currencyDataRepository;

    @Autowired
    public CurrencyRatesServiceImpl(FixerIoApiClient apiClient, RabbitMQSender mqClient, CurrencyDataRepository currencyDataRepository) {
        this.apiClient = apiClient;
        this.mqClient = mqClient;
        this.currencyDataRepository = currencyDataRepository;
    }

    @Override
    public String fetchDataAndStoreInDatabase() {

        CurrencyApiResponse response = apiClient.getCurrencyApiResponse();
        var date = response.getDate();
        var timestamp = response.getTimestamp();

        if (response.isSuccess()) {
            response.getRates().forEach((key, value) -> {

                CurrencyData currencyData = new CurrencyData();
                currencyData.setCurrency(key);
                currencyData.setRate(value);
                currencyData.setDate(date);
                currencyData.setTimestamp(timestamp);

                var result = currencyDataRepository.save(currencyData);

                //todo serialize and send the result
                mqClient.sendMessageToCurrencyStats("new currency stats saved with id=" + result.getId());
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

    @Scheduled(fixedRate = 3600000)
    public void updateCurrencyData() {
        this.fetchDataAndStoreInDatabase();
    }
}
