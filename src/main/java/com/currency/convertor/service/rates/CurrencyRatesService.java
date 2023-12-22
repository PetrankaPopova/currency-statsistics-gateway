package com.currency.convertor.service.rates;

import com.currency.convertor.domain.dto.CurrencyApiRequest;
import com.currency.convertor.domain.dto.CurrencyApiResponse;

public interface CurrencyRatesService {
    String fetchDataAndStoreInDatabase();
    CurrencyApiResponse processExternalService1(CurrencyApiRequest request);
    CurrencyApiResponse processExternalService2(CurrencyApiRequest request);
}
