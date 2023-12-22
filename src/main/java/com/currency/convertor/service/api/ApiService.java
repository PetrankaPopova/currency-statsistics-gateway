package com.currency.convertor.service.api;

import com.currency.convertor.domain.dto.CurrencyStasRequest;
import com.currency.convertor.domain.dto.CurrencyStasResponse;
import com.currency.convertor.domain.dto.XmlRequest;
import com.currency.convertor.domain.dto.XmlResponse;

public interface ApiService {
    CurrencyStasResponse processJsonCurrentRequest(CurrencyStasRequest jsonRequest);
    XmlResponse processXmlCommand(XmlRequest xmlRequest);

    CurrencyStasResponse processJsonHistoryRequest(CurrencyStasRequest jsonRequest);
}
