package com.currency.convertor.service.rates;

import com.currency.convertor.domain.dto.CurrencyApiRequest;
import com.currency.convertor.domain.dto.CurrencyApiResponse;
/**
 * The CurrencyRatesService interface defines the contract for interacting with currency rates-related operations.
 * Implementing classes provide functionalities for fetching, processing, and storing currency rates data.
 *
 * Methods:
 * - fetchDataAndStoreInDatabase: Fetches currency rates data from an external API and stores it in the database.
 * - processExternalService1: Processes an external service request based on the provided CurrencyApiRequest.
 * - processExternalService2: Processes another external service request based on the provided CurrencyApiRequest.
 *
 * Usage Example:
 * - Used as an abstraction for handling currency rates-related operations, providing a consistent interface for
 *   different implementations.
 */
public interface CurrencyRatesService {
    String fetchDataAndStoreInDatabase();
    CurrencyApiResponse processExternalService1(CurrencyApiRequest request);
    CurrencyApiResponse processExternalService2(CurrencyApiRequest request);
}
