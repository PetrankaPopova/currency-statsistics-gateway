package com.currency.convertor.service.api;

import com.currency.convertor.domain.dto.JsonRequest;
import com.currency.convertor.domain.dto.JsonResponse;
import com.currency.convertor.domain.dto.XmlRequest;
import com.currency.convertor.domain.dto.XmlResponse;

public interface ApiService {
    JsonResponse processJsonCurrentRequest(JsonRequest jsonRequest);
    XmlResponse processXmlCommand(XmlRequest xmlRequest);

    JsonResponse processJsonHistoryRequest(JsonRequest jsonRequest);
}
