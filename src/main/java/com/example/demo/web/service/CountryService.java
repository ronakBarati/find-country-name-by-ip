package com.example.demo.web.service;

import com.example.demo.web.handler.RestTemplateResponseErrorHandler;
import com.example.demo.web.model.Country;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class CountryService {

    private final RestTemplate restTemplate;

    @Value("${country.finder.url}")
    private String countryFinderUrl;

    public CountryService(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder
                .errorHandler(new RestTemplateResponseErrorHandler())
                .build();
    }

    public JSONObject findCountryByIp(List<String> ipList) {
        Set<String> countriesName = new LinkedHashSet<>();
        ipList.forEach(ip -> {
            Country country = restTemplate.getForObject(countryFinderUrl + ip, Country.class);
            if (country != null && country.getStatus().equalsIgnoreCase("success"))
                countriesName.add(country.getCountry());
        });
        List<String> countriesNameResult = new ArrayList<>(countriesName);
        Collections.sort(countriesNameResult);

        return createJsonObject(countriesNameResult);
    }

    private JSONObject createJsonObject(List<String> countriesNameResult){
        JSONObject json = new JSONObject();
        json.put("northCountries", countriesNameResult);
        return json;
    }

}
