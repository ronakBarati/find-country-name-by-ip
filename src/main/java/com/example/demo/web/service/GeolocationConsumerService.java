package com.example.demo.web.service;

import com.example.demo.web.model.Geolocation;
import com.example.demo.web.handler.RestTemplateResponseErrorHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GeolocationConsumerService {

    private final RestTemplate restTemplate;

    @Value("${geolocation.url}")
    private String geolocationUrl;

    @Autowired
    public GeolocationConsumerService(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder
                .errorHandler(new RestTemplateResponseErrorHandler())
                .build();
    }

    public Geolocation findCountryByIp(String ip){
        return restTemplate.getForObject(geolocationUrl + ip, Geolocation.class);
    }
}
