package com.assignment.geocoding.ui;

import com.assignment.geocoding.domain.LocationCoordinates;

import java.util.List;
import java.util.Scanner;

public class ConsoleUserInterface {
    private final Scanner scanner;

    public ConsoleUserInterface() {
        this.scanner = new Scanner(System.in);
    }

    public String promptForLocation() {
        System.out.print("Enter a location name (or type 'exit' to quit): ");
        return scanner.nextLine();
    }

    public void displayCoordinates(List<LocationCoordinates> coordinates) {
        if (coordinates.isEmpty()) {
            System.out.println("No coordinates found for the given location.");
            return;
        }

        System.out.println("\n--- Location Results ---");
        for (int index = 0; index < coordinates.size(); index++) {
            System.out.printf("Result %d:\n%s\n", (index + 1), coordinates.get(index).toString());
            System.out.println("------------------------");
        }
    }

    public void displayError(String message) {
        System.err.println("\n[ERROR] " + message + "\n");
    }
    
    public void displayMessage(String message) {
        System.out.println(message);
    }
}
