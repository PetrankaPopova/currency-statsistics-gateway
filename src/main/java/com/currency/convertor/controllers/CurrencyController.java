package com.currency.convertor.controllers;

import com.currency.convertor.domain.dto.CurrencyApiRequest;
import com.currency.convertor.domain.dto.CurrencyApiResponse;
import com.currency.convertor.service.CurrencyService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CurrencyController {
    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }


    @GetMapping("/fetchData")
    public String fetchData() {
        return currencyService.fetchDataAndStoreInDatabase();

    }

    // JSON API
    @PostMapping(value = "/ext-service-1/json", consumes = "application/json", produces = "application/json")
    public CurrencyApiResponse extService1Json(@RequestBody CurrencyApiRequest request) {
        return currencyService.processExternalService1(request);
    }

    // XML API
    @PostMapping(value = "/ext-service-2/xml", consumes = "application/xml", produces = "application/xml")
    public CurrencyApiResponse extService2Xml(@RequestBody CurrencyApiRequest request) {
        return currencyService.processExternalService2(request);
    }
}

