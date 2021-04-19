package com.example.demo.web.service;

import com.example.demo.web.model.Geolocation;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GeolocationService {

    private final GeolocationConsumerService geolocationConsumerService;

    private final static Logger logger = LoggerFactory.getLogger(GeolocationService.class);


    private final Map<String, Geolocation> hemisphereCountryByIp = new HashMap<>();

    public GeolocationService(GeolocationConsumerService geolocationConsumerService) {
        this.geolocationConsumerService = geolocationConsumerService;
    }

    public JSONObject findNorthHemisphereCountryByIp(List<String> ipList) {

        Set<String> northHemisphereCountriesName = new LinkedHashSet<>();
        ipList.forEach(ip -> {
            logger.info("find north country name from cache ....");
            if (hemisphereCountryByIp.containsKey(ip)) {
                if (hemisphereCountryByIp.get(ip).isNorthHemisphereCountry()) {
                    northHemisphereCountriesName.add(hemisphereCountryByIp.get(ip).getCountry());
                }
                logger.info("this ip :{} belongs to south hemisphere ",ip);
            } else {
                logger.info("find north country name by calling service ....");
                Geolocation geolocation = geolocationConsumerService.findCountryByIp(ip);
                if (isValidIp(geolocation) && isNorthHemisphere(geolocation)){
                    northHemisphereCountriesName.add(geolocation.getCountry());
                    hemisphereCountryByIp.put(ip,geolocation);
                }
            }
        });

        List<String> northHemisphereCountriesNameResult = new ArrayList<>(northHemisphereCountriesName);
        Collections.sort(northHemisphereCountriesNameResult);

        return createJsonObject(northHemisphereCountriesNameResult);
    }

    private boolean isNorthHemisphere(Geolocation geolocation) {
        geolocation.setNorthHemisphereCountry(geolocation.getLat() > 0);
        return geolocation.getLat() > 0;
    }

    private boolean isValidIp(Geolocation geolocation) {
        return geolocation != null && geolocation.getStatus().equalsIgnoreCase("success");
    }

    private JSONObject createJsonObject(List<String> countriesNameResult) {
        JSONObject json = new JSONObject();
        json.put("northCountries", countriesNameResult);
        return json;
    }

}
