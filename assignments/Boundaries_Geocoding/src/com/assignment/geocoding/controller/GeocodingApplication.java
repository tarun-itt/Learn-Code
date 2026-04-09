package com.assignment.geocoding.controller;

import com.assignment.geocoding.boundary.GeocodingProvider;
import com.assignment.geocoding.domain.LocationCoordinates;
import com.assignment.geocoding.domain.LocationInput;
import com.assignment.geocoding.exception.GeocodingException;
import com.assignment.geocoding.ui.ConsoleUserInterface;

import java.util.List;

public class GeocodingApplication {
    private final GeocodingProvider geocodingProvider;
    private final ConsoleUserInterface userInterface;

    public GeocodingApplication(GeocodingProvider geocodingProvider, ConsoleUserInterface userInterface) {
        this.geocodingProvider = geocodingProvider;
        this.userInterface = userInterface;
    }

    public void run() {
        userInterface.displayMessage("Welcome to the Geocoding Boundary Application!");
        
        while (true) {
            String locationName = userInterface.promptForLocation();
            if ("exit".equalsIgnoreCase(locationName.trim())) {
                userInterface.displayMessage("Exiting application. Goodbye!");
                break;
            }
            
            processLocationRequest(locationName);
        }
    }

    private void processLocationRequest(String locationName) {
        try {
            LocationInput locationInput = new LocationInput(locationName);
            List<LocationCoordinates> coordinates = geocodingProvider.fetchCoordinates(locationInput);
            userInterface.displayCoordinates(coordinates);
        } catch (GeocodingException exception) {
            userInterface.displayError(exception.getMessage());
        } catch (Exception exception) {
            userInterface.displayError("An unexpected error occurred: " + exception.getMessage());
        }
    }
}
