package com.currency.convertor.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(reason = "requestId must be Unique", code = HttpStatus.BAD_REQUEST)
public class DuplicateRequestIdException extends Exception{
}
