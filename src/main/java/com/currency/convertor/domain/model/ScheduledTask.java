package com.currency.convertor.domain.model;

import com.currency.convertor.service.CurrencyService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {

    private CurrencyService currencyService;

    @Scheduled(fixedRate = 3600000)
    public void updateCurrencyData() {
        if (currencyService == null){
            return;

        }
        currencyService.fetchDataAndStoreInDatabase();
    }
}
