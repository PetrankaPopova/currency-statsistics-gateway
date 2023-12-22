package com.currency.convertor.controllers;

import com.currency.convertor.domain.dto.CurrencyStasRequest;
import com.currency.convertor.domain.dto.CurrencyStasResponse;
import com.currency.convertor.service.statistics.CurrencyStatisticsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/json_api")
public class StatisticsController {
    // Assuming you have a service class to handle the logic
    private final CurrencyStatisticsService currencyStatisticsService;

    public StatisticsController(CurrencyStatisticsService currencyStatisticsService) {
        this.currencyStatisticsService = currencyStatisticsService;
    }

    @PostMapping("/current")
    public CurrencyStasResponse processJsonCurrentRequest(@RequestBody CurrencyStasRequest jsonRequest) {
        return currencyStatisticsService.processJsonCurrentRequest(jsonRequest);
    }

    @PostMapping("/history")
    public CurrencyStasResponse processJsonHistoryRequest(@RequestBody CurrencyStasRequest jsonRequest) {
        return currencyStatisticsService.processJsonHistoryRequest(jsonRequest);
    }
}
