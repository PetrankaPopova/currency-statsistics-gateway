package com.currency.convertor.controllers;

import com.currency.convertor.domain.dto.CurrencyApiRequest;
import com.currency.convertor.domain.dto.CurrencyApiResponse;
import com.currency.convertor.service.rates.CurrencyRatesService;
import org.springframework.web.bind.annotation.*;
/**
 * The CurrencyController class is a Spring MVC controller annotated with @RestController, indicating that
 * it handles RESTful API requests. It is mapped to the "/api" path. This controller is responsible for
 * exposing endpoints related to currency data.
 *
 * The class contains a constructor that injects a CurrencyRatesService, allowing the controller to interact
 * with the business logic related to currency rates.
 *
 * The fetchData() method is annotated with @GetMapping and is mapped to the "/fetchData" endpoint. It delegates
 * the request to the CurrencyRatesService to fetch and store currency data in the database, and returns the
 * result as a String. This method is likely used for triggering the update of currency data from an external API.
 */
@RestController
@RequestMapping("/api")
public class CurrencyController {
    private final CurrencyRatesService currencyRatesService;

    public CurrencyController(CurrencyRatesService currencyRatesService) {
        this.currencyRatesService = currencyRatesService;
    }

    @GetMapping("/fetchData")
    public String fetchData() {
        return currencyRatesService.fetchDataAndStoreInDatabase();
    }


}

