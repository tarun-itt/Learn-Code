package com.assignment.geocoding.infrastructure;

import com.assignment.geocoding.boundary.GeocodingProvider;
import com.assignment.geocoding.domain.LocationCoordinates;
import com.assignment.geocoding.domain.LocationInput;
import com.assignment.geocoding.exception.GeocodingException;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class GoogleGeocodingProvider implements GeocodingProvider {
    private static final String API_URL = "https://maps.googleapis.com/maps/api/geocode/json?address=%s&key=%s";
    
    private final String apiKey;
    private final HttpClient httpClient;
    private final SimpleJsonExtractor jsonExtractor;

    public GoogleGeocodingProvider(String apiKey, SimpleJsonExtractor jsonExtractor) {
        this.apiKey = apiKey;
        this.jsonExtractor = jsonExtractor;
        this.httpClient = HttpClient.newHttpClient();
    }

    @Override
    public List<LocationCoordinates> fetchCoordinates(LocationInput locationInput) {
        String encodedLocation = URLEncoder.encode(locationInput.getLocationName(), StandardCharsets.UTF_8);
        String requestUri = String.format(API_URL, encodedLocation, apiKey);
        
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(requestUri))
                .GET()
                .build();
                
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            
            if (response.statusCode() != 200) {
                throw new GeocodingException("Failed to fetch data from API. HTTP Status: " + response.statusCode());
            }
            
            return jsonExtractor.extractCoordinates(response.body());
            
        } catch (IOException | InterruptedException exception) {
            Thread.currentThread().interrupt();
            throw new GeocodingException("Network error occurred while contacting Geocoding API.", exception);
        }
    }
}
