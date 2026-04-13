package com.assignment.geocoding.domain;

public class LocationCoordinates {
    private final double latitude;
    private final double longitude;
    private final String formattedAddress;

    public LocationCoordinates(double latitude, double longitude, String formattedAddress) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.formattedAddress = formattedAddress;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getFormattedAddress() {
        return formattedAddress;
    }

    @Override
    public String toString() {
        return String.format("Address: %s\nLatitude: %f\nLongitude: %f", formattedAddress, latitude, longitude);
    }
}
