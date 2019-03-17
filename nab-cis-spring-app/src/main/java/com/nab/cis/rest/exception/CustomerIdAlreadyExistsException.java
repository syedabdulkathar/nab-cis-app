package com.nab.cis.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CustomerIdAlreadyExistsException extends RuntimeException {

    public CustomerIdAlreadyExistsException() {
    }

    public CustomerIdAlreadyExistsException(String message) {
        super(message);
    }

    public CustomerIdAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
