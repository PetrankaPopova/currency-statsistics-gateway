package com.currency.convertor.controllers;

import com.currency.convertor.domain.dto.CurrencyStasRequest;
import com.currency.convertor.domain.dto.XmlRequest;
import com.currency.convertor.domain.dto.XmlResponse;
import com.currency.convertor.exception.DuplicateRequestIdException;
import com.currency.convertor.service.statistics.CurrencyStatisticsService;
import org.apache.tomcat.jni.Time;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequestMapping("/xml_api")
public class StatsXmlController {

    private final CurrencyStatisticsService currencyStatisticsService;

    public StatsXmlController(CurrencyStatisticsService currencyStatisticsService) {
        this.currencyStatisticsService = currencyStatisticsService;
    }

    @PostMapping(consumes = "application/xml", produces = "application/xml", value = "/command")
    XmlResponse processCommand(@RequestBody XmlRequest xmlRequest) throws DuplicateRequestIdException {
        if (xmlRequest.getHistory() != null) {
            var historyReq = xmlRequest.getHistory();
            var statsReq = new CurrencyStasRequest();
            statsReq.setClient(historyReq.getConsumer());
            statsReq.setCurrency(historyReq.getCurrency());
            statsReq.setTimestamp(Instant.now().getEpochSecond());
            statsReq.setPeriod(historyReq.getPeriod());
            statsReq.setRequestId(xmlRequest.getId());
            var result = currencyStatisticsService.processCurrencyHistoryRequest(statsReq);
            return new XmlResponse(xmlRequest.getId(), historyReq.getConsumer(), historyReq.getCurrency(), null, result.getStats(), result.getPeriod());
        } else {
            var statsReq = new CurrencyStasRequest();
            statsReq.setClient(xmlRequest.getGet().getConsumer());
            statsReq.setCurrency(xmlRequest.getGet().getCurrency());
            statsReq.setTimestamp(Instant.now().getEpochSecond());
            statsReq.setRequestId(xmlRequest.getId());
            var result = currencyStatisticsService.processCurrencyStatsRequest(statsReq);
            return new XmlResponse(xmlRequest.getId(), result.getConsumer(), result.getCurrency(), result.getData(), null, null);
        }
    }
}
