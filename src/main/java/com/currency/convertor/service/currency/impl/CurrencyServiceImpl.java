package com.currency.convertor.service.currency.impl;

import com.currency.convertor.domain.dto.CurrencyApiRequest;
import com.currency.convertor.domain.entity.CurrencyData;
import com.currency.convertor.domain.dto.CurrencyApiResponse;
import com.currency.convertor.repository.CurrencyDataRepository;
import com.currency.convertor.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CurrencyServiceImpl implements CurrencyService {
    private static final String FIXER_API_URL = "http://data.fixer.io/api/latest";
    private static final String ACCESS_KEY = "c776327ea18e75aabe3917ffd6d5bfb6";

    @Value("${fixer.api.key}")
    private String fixerApiKey;

    private final CurrencyDataRepository currencyDataRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public CurrencyServiceImpl(CurrencyDataRepository currencyDataRepository, RestTemplate restTemplate) {
        this.currencyDataRepository = currencyDataRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public String fetchDataAndStoreInDatabase() {
        String apiUrl = FIXER_API_URL + "?access_key=" + ACCESS_KEY;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiUrl, String.class);

        var statusCode = responseEntity.getStatusCode();
        String responseBody = responseEntity.getBody();
        HttpHeaders headers = responseEntity.getHeaders();

        /*System.out.println("Status Code: " + statusCode);
        System.out.println("Response Body: " + responseBody);
        System.out.println("Headers: " + headers);*/

        CurrencyApiResponse response = restTemplate.getForObject(apiUrl, CurrencyApiResponse.class);


        if (response != null && response.isSuccess()) {
            response.getRates().forEach((key, value) -> {
                CurrencyData currencyData = new CurrencyData();
                currencyData.setCurrency(key);
                currencyData.setRate(value);
                currencyDataRepository.save(currencyData);
            });

        }

        return apiUrl;
    }

    @Override
    public CurrencyApiResponse processExternalService1(CurrencyApiRequest request) {
        String apiUrl = FIXER_API_URL + "?access_key=" + ACCESS_KEY;
        CurrencyApiResponse response = restTemplate.getForObject(apiUrl, CurrencyApiResponse.class);
        return new CurrencyApiResponse(request.getCurrency(), response.getRates().get(request.getCurrency()));
    }

    @Override
    public CurrencyApiResponse processExternalService2(CurrencyApiRequest request) {
        String apiUrl = FIXER_API_URL + "?access_key=" + ACCESS_KEY;
        CurrencyApiResponse response = restTemplate.getForObject(apiUrl, CurrencyApiResponse.class);
        return new CurrencyApiResponse(request.getCurrency(), response.getRates().get(request.getCurrency()) * 2.0);
    }
}
