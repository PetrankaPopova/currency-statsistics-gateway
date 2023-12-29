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
/**
 * The FixerIoApiClient class is responsible for interacting with the Fixer.io API
 * to fetch currency exchange rates and related data. It provides methods to retrieve
 * exchange rates for specific currencies, fetch a complete API response, and obtain
 * the API URL with the configured access key. The class utilizes the RestTemplate
 * for making HTTP requests and Jackson ObjectMapper for parsing JSON responses.
 *
 * Note: The class also includes methods for fetching data directly from Fixer.io using
 * HttpClient, but the primary methods rely on RestTemplate for better integration
 * with Spring Boot applications.
 */
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
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(fixerApiUrl + "?access_key=" + fixerApiKey + "&base=USD&symbols=" + currency)).build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            Map<String, Object> responseData = parseJsonResponse(response.body());

            if (responseData.containsKey("rates")) {
                Map<String, Double> rates = (Map<String, Double>) responseData.get("rates");
                if (rates.containsKey(currency)) {
                    return rates.get(currency);
                }
            }


            throw new RuntimeException("Unable to fetch data from Fixer.io for currency: " + currency);
        } catch (Exception e) {
            throw new RuntimeException("Error fetching data from Fixer.io", e);
        }
    }

    private Map parseJsonResponse(String jsonResponse) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(jsonResponse, Map.class);
        } catch (Exception e) {
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
