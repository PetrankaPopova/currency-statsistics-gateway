package com.currency.convertor.service.statistics;

import com.currency.convertor.domain.dto.*;
import com.currency.convertor.exception.DuplicateRequestIdException;
/**
 * The CurrencyStatisticsService interface defines methods for processing requests related to currency statistics.
 *
 * Key Methods:
 * - processCurrencyStatsRequest: Processes a request for current currency statistics, handling duplicate request IDs.
 * - processCurrencyHistoryRequest: Processes a request for historical currency statistics within a specified period,
 *   handling duplicate request IDs.
 *
 * Exceptions:
 * - DuplicateRequestIdException: Thrown if a duplicate request ID is detected during processing.
 *
 * Usage Example:
 * - Implemented by service classes providing functionality to handle currency statistics requests.
 * - Used by controllers to delegate the processing of currency statistics requests.
 * - Ensures a standardized interface for processing different types of currency statistics requests.
 */
public interface CurrencyStatisticsService {
    /**
     * Processes a request for current currency statistics, handling duplicate request IDs.
     *
     * @param currencyStasRequest The request containing details for current currency statistics.
     * @return CurrencyStasResponse containing the processed response.
     * @throws DuplicateRequestIdException Thrown if a duplicate request ID is detected.
     */
    CurrencyStasResponse processCurrencyStatsRequest(CurrencyStasRequest currencyStasRequest) throws DuplicateRequestIdException;
    /**
     * Processes a request for historical currency statistics within a specified period, handling duplicate request IDs.
     *
     * @param currencyStasRequest The request containing details for historical currency statistics.
     * @return CurrencyStatsHistoryResponse containing the processed response.
     * @throws DuplicateRequestIdException Thrown if a duplicate request ID is detected.
     */
    CurrencyStatsHistoryResponse processCurrencyHistoryRequest(CurrencyStasRequest currencyStasRequest) throws DuplicateRequestIdException ;
}
