package com.currency.convertor.scheduled;

import com.currency.convertor.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {

    private final CurrencyService currencyService;

    @Autowired
    public ScheduledTask(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @Scheduled(fixedRate = 3600000)
    public void updateCurrencyData() {
        if (currencyService == null){
            return;
        }
        currencyService.fetchDataAndStoreInDatabase();
    }
}
