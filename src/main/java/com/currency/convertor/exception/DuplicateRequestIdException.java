package com.currency.convertor.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * The DuplicateRequestIdException class represents a custom exception that is thrown when a request with a duplicate
 * requestId is detected. This exception is annotated with @ResponseStatus to indicate the HTTP status code and reason
 * to be returned in case of an occurrence.
 *
 * Custom Annotations:
 * - @ResponseStatus: Indicates the response status to be returned when this exception is thrown.
 *
 * Usage Example:
 * - This exception is thrown when a duplicate requestId is detected, indicating a bad request.
 */

@ResponseStatus(reason = "requestId must be Unique", code = HttpStatus.BAD_REQUEST)
public class DuplicateRequestIdException extends Exception{
}
