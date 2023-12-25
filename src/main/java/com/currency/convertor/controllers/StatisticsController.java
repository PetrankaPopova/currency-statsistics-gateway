package com.currency.convertor.controllers;

import com.currency.convertor.domain.dto.CurrencyStasRequest;
import com.currency.convertor.domain.dto.CurrencyStasResponse;
import com.currency.convertor.domain.dto.CurrencyStatsHistoryResponse;
import com.currency.convertor.exception.DuplicateRequestIdException;
import com.currency.convertor.service.statistics.CurrencyStatisticsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/json_api")
public class StatisticsController {
    private final CurrencyStatisticsService currencyStatisticsService;

    public StatisticsController(CurrencyStatisticsService currencyStatisticsService) {
        this.currencyStatisticsService = currencyStatisticsService;
    }

    @PostMapping("/current")
    public CurrencyStasResponse processJsonCurrentRequest(@RequestBody CurrencyStasRequest jsonRequest) throws DuplicateRequestIdException {
        return currencyStatisticsService.processCurrencyStatsRequest(jsonRequest);
    }

    @PostMapping("/history")
    public CurrencyStatsHistoryResponse processJsonHistoryRequest(@RequestBody CurrencyStasRequest jsonRequest) throws DuplicateRequestIdException {
        return currencyStatisticsService.processCurrencyHistoryRequest(jsonRequest);
    }
}
