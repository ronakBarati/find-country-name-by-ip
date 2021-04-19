package com.example.demo.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class GeolocationNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(GeolocationNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String geolocationNotFoundHandler(GeolocationNotFoundException ex) {
        return ex.getMessage();
    }
}