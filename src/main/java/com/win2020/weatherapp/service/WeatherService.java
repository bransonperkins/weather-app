package com.win2020.weatherapp.service;

import com.win2020.weatherapp.model.Response;
import com.win2020.weatherapp.model.ZipCode;
import com.win2020.weatherapp.repository.ZipCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class WeatherService {

    @Autowired
    private ZipCodeRepository zipCodeRepository;

    @Value(value = "${api_key}")
    private String apiKey;

    public List<ZipCode> getRecentSearches() {
        return zipCodeRepository.findAll();
    }

    public Response getForecast(String zipCode) {
        String url = "http://api.openweathermap.org/data/2.5/weather?zip=" +
                zipCode + "&units=imperial&appid=" + apiKey;
        RestTemplate restTemplate = new RestTemplate();
        ZipCode zip = new ZipCode(zipCode);
        try {
            if (zipCodeRepository.findByZipCode(zipCode) == null) {
                zipCodeRepository.save(zip);
            }
            return restTemplate.getForObject(url, Response.class);
        }
        catch (HttpClientErrorException ex) {
            Response response = new Response();
            response.setName("error");
            return response;
        }

    }

}
