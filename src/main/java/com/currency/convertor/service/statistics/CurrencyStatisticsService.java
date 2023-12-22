package com.currency.convertor.service.statistics;

import com.currency.convertor.domain.dto.CurrencyStasRequest;
import com.currency.convertor.domain.dto.CurrencyStasResponse;
import com.currency.convertor.domain.dto.XmlRequest;
import com.currency.convertor.domain.dto.XmlResponse;
import org.springframework.dao.DuplicateKeyException;

public interface CurrencyStatisticsService {
    CurrencyStasResponse processJsonCurrentRequest(CurrencyStasRequest jsonRequest) throws DuplicateKeyException;
    XmlResponse processXmlCommand(XmlRequest xmlRequest);

    CurrencyStasResponse processJsonHistoryRequest(CurrencyStasRequest jsonRequest);
}
