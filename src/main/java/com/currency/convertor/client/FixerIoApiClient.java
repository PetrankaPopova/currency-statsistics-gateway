package com.currency.convertor.client;

import com.currency.convertor.domain.dto.CurrencyApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

@Service
public class FixerIoApiClient {

    private final RestTemplate restTemplate;

    @Value("${fixer.api.key}")
    private String fixerApiKey;

    @Value("${fixer.api.url}")
    private String fixerApiUrl;

    @Autowired
    public FixerIoApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public double fetchDataFromFixerIO(String currency) {
        try {

            // Create an HTTP client
            HttpClient httpClient = HttpClient.newHttpClient();

            System.out.println(fixerApiKey);
            System.out.println(fixerApiUrl);
            // Create an HTTP request
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(fixerApiUrl + "?access_key=" + fixerApiKey + "&base=USD&symbols=" + currency)).build();

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

    public CurrencyApiResponse getCurrencyApiResponse() {
        return restTemplate.getForObject(fixerApiUrl + "?access_key=" + fixerApiKey, CurrencyApiResponse.class);
    }

    public String getApiUrl() {
        return fixerApiUrl + "?access_key=" + fixerApiKey;
    }

    public ResponseEntity<String> getStringResponseEntity() {
        return restTemplate.getForEntity(fixerApiUrl + "?access_key=" + fixerApiKey, String.class);
    }
}
