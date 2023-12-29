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
/**
 * The StatisticsController class is a Spring MVC controller annotated with @RestController, indicating that
 * it handles RESTful API requests. It is mapped to the "/json_api" path. This controller is responsible for
 * exposing endpoints related to currency statistics in JSON format.
 *
 * The class contains a constructor that injects a CurrencyStatisticsService, allowing the controller to interact
 * with the business logic related to currency statistics.
 *
 * The processJsonCurrentRequest() method is annotated with @PostMapping and is mapped to the "/current" endpoint.
 * It receives a JSON request body of type CurrencyStasRequest, processes the request using the CurrencyStatisticsService,
 * and returns the response as a CurrencyStasResponse. This method likely handles requests for current currency statistics.
 *
 * The processJsonHistoryRequest() method is annotated with @PostMapping and is mapped to the "/history" endpoint.
 * Similar to the previous method, it receives a JSON request body of type CurrencyStasRequest, processes the request
 * using the CurrencyStatisticsService, and returns the response as a CurrencyStatsHistoryResponse. This method likely
 * handles requests for historical currency statistics.
 */
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
