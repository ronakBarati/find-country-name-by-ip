package com.example.demo.web.exception;

public class CountryNotFoundException extends RuntimeException {
    public CountryNotFoundException() {
        super("Could not find Country ");
    }
}
