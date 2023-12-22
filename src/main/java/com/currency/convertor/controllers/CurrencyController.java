package com.currency.convertor.controllers;

import com.currency.convertor.domain.dto.CurrencyApiRequest;
import com.currency.convertor.domain.dto.CurrencyApiResponse;
import com.currency.convertor.service.rates.CurrencyRatesService;
import org.springframework.web.bind.annotation.*;

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

    // JSON API
    @PostMapping(value = "/ext-service-1/json", consumes = "application/json", produces = "application/json")
    public CurrencyApiResponse extService1Json(@RequestBody CurrencyApiRequest request) {
        return currencyRatesService.processExternalService1(request);
    }

    // XML API
    @PostMapping(value = "/ext-service-2/xml", consumes = "application/xml", produces = "application/xml")
    public CurrencyApiResponse extService2Xml(@RequestBody CurrencyApiRequest request) {
        return currencyRatesService.processExternalService2(request);
    }
}

