package com.example.weatherApp.services;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.Scanner;


public class WeatherAPIService {

    private static final String CONFIG_FILE_PATH = "application.properties";
    private JsonNode data;

    public WeatherAPIService(double latitude, double longitude) {
        createConnection(latitude,longitude);
    }

    public void createConnection(double latitude, double longitude){
        try{
            Properties properties = new Properties();
            InputStream input = WeatherAPIService.class.getClassLoader().getResourceAsStream(CONFIG_FILE_PATH);
            if(input == null){
                System.err.println("Unable to find file: " + CONFIG_FILE_PATH);
                return;
            }
            properties.load(input);
            String API_KEY = properties.getProperty("api.key");
            String APIRequestFormat = "https://api.openweathermap.org/data/2.5/weather?lat=%f&lon=%f&appid=%s";

            String APIRequestURL = String.format(APIRequestFormat,latitude,longitude,API_KEY);
            URL URL = new URL(APIRequestURL);

            HttpURLConnection HttpConnection = (HttpURLConnection) URL.openConnection();
            HttpConnection.setRequestMethod("GET");
            HttpConnection.connect();

            int responseCode = HttpConnection.getResponseCode();
            if(responseCode != 200){
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            }
            else{
                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(URL.openStream());

                while(scanner.hasNext()){
                    informationString.append(scanner.nextLine());
                }

                scanner.close();
                this.data = JSON.parse(String.valueOf(informationString));
            }
        }
        catch(Exception e){ //throws MalformedURLException, IOException, RuntimeException, ParseException
            System.out.println("Caught a generic exception when creating an API Connection");
        }
    }

    public JsonNode getAPIResponseObject(){
        return this.data;
    }
}
