package com.currency.convertor.service;

import com.currency.convertor.domain.dto.CurrencyApiRequest;
import com.currency.convertor.domain.dto.CurrencyApiResponse;

public interface CurrencyService {
    String fetchDataAndStoreInDatabase();
    CurrencyApiResponse processExternalService1(CurrencyApiRequest request);
    CurrencyApiResponse processExternalService2(CurrencyApiRequest request);
}
