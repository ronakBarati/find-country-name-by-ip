package com.example.demo;

import com.example.demo.web.service.GeolocationService;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Size;
import java.util.List;

@RestController
@Validated
public class GeolocationController {

    final GeolocationService geolocationService;

    public GeolocationController(GeolocationService geolocationService) {
        this.geolocationService = geolocationService;
    }

    @RequestMapping(value = "/northCountries", method = RequestMethod.GET)
    public ResponseEntity<JSONObject> listAllCountries(@RequestParam("ip") @Size(min = 1 , max = 5) List<String> ipList) {
        JSONObject result = geolocationService.findNorthHemisphereCountryByIp(ipList);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
