package com.assignment.geocoding.domain;

import com.assignment.geocoding.exception.GeocodingException;

public class LocationInput {
    private final String locationName;

    public LocationInput(String locationName) {
        validateName(locationName);
        this.locationName = locationName.trim();
    }

    private void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new GeocodingException("Location name cannot be empty or null.");
        }
    }

    public String getLocationName() {
        return locationName;
    }
}
