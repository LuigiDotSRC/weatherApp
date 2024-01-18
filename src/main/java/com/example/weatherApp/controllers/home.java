package com.example.weatherApp.controllers;

import com.example.weatherApp.services.WeatherAPIService;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class home {

    @GetMapping("")
    public String home_view(Model model){
        double TorontoLong = 43.6532;
        double TorontoLat = -79.3832;

        WeatherAPIService WeatherService = new WeatherAPIService(TorontoLat,TorontoLong);
        JSONObject APIResponse = WeatherService.getAPIResponseObject();

        double longitude = (double)( (JSONObject)(APIResponse.get("coord")) ).get("lon");
        double latitude = (double)( (JSONObject)(APIResponse.get("coord")) ).get("lat");

        model.addAttribute("longitude",longitude);
        model.addAttribute("latitude",latitude);
        return "home_view";
    }
}
