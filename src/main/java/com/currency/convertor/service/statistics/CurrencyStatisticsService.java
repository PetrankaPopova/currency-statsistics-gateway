package com.currency.convertor.service.statistics;

import com.currency.convertor.domain.dto.*;
import com.currency.convertor.exception.DuplicateRequestIdException;

public interface CurrencyStatisticsService {
    CurrencyStasResponse processCurrencyStatsRequest(CurrencyStasRequest currencyStasRequest) throws DuplicateRequestIdException;
    CurrencyStatsHistoryResponse processCurrencyHistoryRequest(CurrencyStasRequest currencyStasRequest) throws DuplicateRequestIdException ;
}
