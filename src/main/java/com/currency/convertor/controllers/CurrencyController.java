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


}

