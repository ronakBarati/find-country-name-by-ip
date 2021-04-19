package com.example.demo.web.exception;

public class GeolocationNotFoundException extends RuntimeException {
    public GeolocationNotFoundException() {
        super("Could not find geolocation ");
    }
}
