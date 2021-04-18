package com.example.demo;

import com.example.demo.web.service.CountryService;
import net.minidev.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RunWith(SpringRunner.class)
@WebMvcTest(value = CountryController.class)
@WithMockUser(username = "admin", roles = "ADMIN")
public class DemoApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CountryService countryService;

    List<String> countriesName = new ArrayList<>(Arrays.asList("Canada", "Iran"));

    @Test
    public void retrieveCountriesName() throws Exception {

        JSONObject json = new JSONObject();
        json.put("northCountries", countriesName);

        Mockito.when(
                countryService.findCountryByIp(Mockito.anyList())).thenReturn(json);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/northCountries/?ip=24.48.0.1&ip=185.135.28.12").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "{\"northCountries\":[\"Canada\",\"Iran\"]}";


        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }

}

