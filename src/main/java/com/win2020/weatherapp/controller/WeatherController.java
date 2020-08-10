package com.win2020.weatherapp.controller;

import com.win2020.weatherapp.model.Request;
import com.win2020.weatherapp.model.Response;
import com.win2020.weatherapp.model.ZipCode;
import com.win2020.weatherapp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/")
    public String getIndex(Model model) {
        List<ZipCode> zipCodeList = weatherService.getRecentSearches();
        model.addAttribute("request", new Request());
        model.addAttribute("zip_codes", zipCodeList);
        return "index";
    }

    @PostMapping("/")
    public String postIndex(Request request, Model model) {
        List<ZipCode> zipCodeList = weatherService.getRecentSearches();
        Response data = weatherService.getForecast(request.getZipCode());
        model.addAttribute("data", data);
        model.addAttribute("zip_codes", zipCodeList);
        return "index";
    }

}
