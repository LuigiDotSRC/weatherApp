package com.example.weatherApp.services;

import org.json.simple.JSONObject;

import java.io.InputStream;
import java.util.Properties;

public class GeoLocatorAPIService {

    // API REQUEST FORMAT: http://api.openweathermap.org/geo/1.0/direct?q={city name},{state code},{country code}&limit={limit}&appid={API key}

    final static String CONFIG_FILE_PATH = "application.properties";
    private JSONObject data;

    public GeoLocatorAPIService(String city){
        Properties properties = new Properties();
        InputStream input = WeatherAPIService.class.getClassLoader().getResourceAsStream(CONFIG_FILE_PATH);
        String APIRequestFormat = "http://api.openweathermap.org/geo/1.0/direct?q=%s,%s,%s&limit=%d&appid=%s";

    }
}
