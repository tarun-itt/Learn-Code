package com.assignment.geocoding.config;

import com.assignment.geocoding.exception.GeocodingException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class Configuration {
    private static final String CONFIG_FILE = "config.properties";
    private static final String API_KEY_PROPERTY = "google.api.key";
    
    private final Properties properties;

    public Configuration() {
        this.properties = new Properties();
        loadConfiguration();
    }

    private void loadConfiguration() {
        Path configPath = Paths.get(CONFIG_FILE);
        if (!Files.exists(configPath)) {
            throw new GeocodingException("Configuration file missing: " + CONFIG_FILE);
        }
        
        try (InputStream inputStream = Files.newInputStream(configPath)) {
            properties.load(inputStream);
        } catch (IOException exception) {
            throw new GeocodingException("Failed to load configuration file.", exception);
        }
    }

    public String getApiKey() {
        String apiKey = properties.getProperty(API_KEY_PROPERTY);
        if (apiKey == null || apiKey.trim().isEmpty()) {
            throw new GeocodingException("API key not found in configuration: " + API_KEY_PROPERTY);
        }
        return apiKey.trim();
    }
}
