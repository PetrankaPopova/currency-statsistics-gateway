package com.currency.convertor.domain.client;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class FixerIoApiClient {
    private static final String FIXER_API_URL = "https://api.fixer.io/latest";

    public double fetchDataFromFixerIO(String currency) {
        try {
            // Build the URL with the base URL and the desired currency
            URI uri = URI.create(FIXER_API_URL + "?base=USD&symbols=" + currency);

            // Create an HTTP client
            HttpClient httpClient = HttpClient.newHttpClient();

            // Create an HTTP request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .build();

            // Send the request and receive the response as a JSON string
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            // Parse the JSON response
            Map<String, Object> responseData = parseJsonResponse(response.body());

            // Extract the exchange rate for the specified currency
            if (responseData.containsKey("rates")) {
                Map<String, Double> rates = (Map<String, Double>) responseData.get("rates");
                if (rates.containsKey(currency)) {
                    return rates.get(currency);
                }
            }

            // Handle the case when the currency is not found or there is an issue with the response
            throw new RuntimeException("Unable to fetch data from Fixer.io for currency: " + currency);
        } catch (Exception e) {
            // Handle exceptions such as IOException or InterruptedException
            throw new RuntimeException("Error fetching data from Fixer.io", e);
        }
    }

    private Map parseJsonResponse(String jsonResponse) {
        try {
            // Create an ObjectMapper instance (Jackson library)
            ObjectMapper objectMapper = new ObjectMapper();

            // Parse the JSON string into a Map<String, Object>
            return objectMapper.readValue(jsonResponse, Map.class);
        } catch (Exception e) {
            // Handle exceptions during JSON parsing
            throw new RuntimeException("Error parsing JSON response", e);
        }
    }

    public static void main(String[] args) {
        FixerIoApiClient apiClient = new FixerIoApiClient();
        double data = apiClient.fetchDataFromFixerIO("EUR");
        System.out.println("Exchange rate for EUR: " + data);
    }
}
