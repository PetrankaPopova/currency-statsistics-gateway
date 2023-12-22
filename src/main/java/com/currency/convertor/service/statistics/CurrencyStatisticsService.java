package com.currency.convertor.service.statistics;

import com.currency.convertor.domain.dto.*;
import com.currency.convertor.exception.DuplicateRequestIdException;
import org.springframework.dao.DuplicateKeyException;

public interface CurrencyStatisticsService {
    CurrencyStasResponse processJsonCurrentRequest(CurrencyStasRequest jsonRequest) throws DuplicateRequestIdException;
    XmlResponse processXmlCommand(XmlRequest xmlRequest);

    CurrencyStatsHistoryResponse processJsonHistoryRequest(CurrencyStasRequest jsonRequest) throws DuplicateRequestIdException ;
}
