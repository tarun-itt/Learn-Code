package com.assignment.geocoding;

import com.assignment.geocoding.boundary.GeocodingProvider;
import com.assignment.geocoding.config.Configuration;
import com.assignment.geocoding.controller.GeocodingApplication;
import com.assignment.geocoding.infrastructure.GoogleGeocodingProvider;
import com.assignment.geocoding.infrastructure.SimpleJsonExtractor;
import com.assignment.geocoding.ui.ConsoleUserInterface;

public class Main {
    public static void main(String[] args) {
        try {
            Configuration configuration = new Configuration();
            String apiKey = configuration.getApiKey();
            
            SimpleJsonExtractor jsonExtractor = new SimpleJsonExtractor();
            GeocodingProvider provider = new GoogleGeocodingProvider(apiKey, jsonExtractor);
            
            ConsoleUserInterface userInterface = new ConsoleUserInterface();
            GeocodingApplication application = new GeocodingApplication(provider, userInterface);
            
            application.run();
        } catch (Exception exception) {
            System.err.println("Failed to start application: " + exception.getMessage());
        }
    }
}
