package com.example.landroutes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NoLandRouteException extends RuntimeException {
    public NoLandRouteException(String origin, String destination) {
        super(String.format("No land route from %S to %S", origin, destination));
    }
}