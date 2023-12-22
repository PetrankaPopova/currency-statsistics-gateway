package com.currency.convertor.controllers;

import com.currency.convertor.domain.dto.JsonRequest;
import com.currency.convertor.domain.dto.JsonResponse;
import com.currency.convertor.service.api.ApiService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/json_api")
public class JsonApiController {
    // Assuming you have a service class to handle the logic
    private final ApiService apiService;

    public JsonApiController(ApiService apiService) {
        this.apiService = apiService;
    }

    @PostMapping("/current")
    public JsonResponse processJsonCurrentRequest(@RequestBody JsonRequest jsonRequest) {
        return apiService.processJsonCurrentRequest(jsonRequest);
    }

    @PostMapping("/history")
    public JsonResponse processJsonHistoryRequest(@RequestBody JsonRequest jsonRequest) {
        return apiService.processJsonHistoryRequest(jsonRequest);
    }
}
