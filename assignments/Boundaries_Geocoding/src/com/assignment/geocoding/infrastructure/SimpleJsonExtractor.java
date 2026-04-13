package com.assignment.geocoding.infrastructure;

import com.assignment.geocoding.domain.LocationCoordinates;
import com.assignment.geocoding.exception.GeocodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleJsonExtractor {
    
    private static final Pattern STATUS_PATTERN = Pattern.compile("\"status\"\\s*:\\s*\"([^\"]+)\"");
    private static final Pattern LAT_PATTERN = Pattern.compile("\"lat\"\\s*:\\s*([\\d.-]+)");
    private static final Pattern LNG_PATTERN = Pattern.compile("\"lng\"\\s*:\\s*([\\d.-]+)");

    public List<LocationCoordinates> extractCoordinates(String jsonResponse) {
        validateStatus(jsonResponse);
        
        List<LocationCoordinates> results = new ArrayList<>();
        
        String[] resultBlocks = jsonResponse.split("\"formatted_address\"\\s*:\\s*\"");
        
        for (int index = 1; index < resultBlocks.length; index++) {
            String block = resultBlocks[index];
            
            int quoteIndex = block.indexOf('\"');
            if (quoteIndex == -1) {
                continue;
            }
            
            String address = block.substring(0, quoteIndex);
            
            Matcher latMatcher = LAT_PATTERN.matcher(block);
            Matcher lngMatcher = LNG_PATTERN.matcher(block);
            
            if (latMatcher.find() && lngMatcher.find()) {
                double latitude = Double.parseDouble(latMatcher.group(1));
                double longitude = Double.parseDouble(lngMatcher.group(1));
                results.add(new LocationCoordinates(latitude, longitude, address));
            }
        }
        
        return results;
    }
    
    private void validateStatus(String jsonResponse) {
        Matcher statusMatcher = STATUS_PATTERN.matcher(jsonResponse);
        if (statusMatcher.find()) {
            String status = statusMatcher.group(1);
            if (!"OK".equals(status) && !"ZERO_RESULTS".equals(status)) {
                throw new GeocodingException("API returned error status: " + status);
            }
        } else {
             throw new GeocodingException("Could not determine API status from response.");
        }
    }
}
