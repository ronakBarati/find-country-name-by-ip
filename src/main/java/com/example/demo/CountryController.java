package com.example.demo;

import com.example.demo.web.service.CountryService;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Size;
import java.util.List;

@RestController
public class CountryController {

    final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @RequestMapping(value = "/northCountries", method = RequestMethod.GET)
    public ResponseEntity<JSONObject> listAllCountries(@Size(min = 1) @Size(max = 5) @RequestParam("ip") List<String> ipList) {
        JSONObject result = countryService.findCountryByIp(ipList);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
