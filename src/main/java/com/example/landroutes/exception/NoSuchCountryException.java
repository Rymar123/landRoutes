package com.example.landroutes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NoSuchCountryException extends RuntimeException {
    public NoSuchCountryException(String identifier) {
        super("Illegal country identifier " + identifier);
    }
}