package com.assignment.geocoding.boundary;

import com.assignment.geocoding.domain.LocationCoordinates;
import com.assignment.geocoding.domain.LocationInput;
import java.util.List;

public interface GeocodingProvider {
    List<LocationCoordinates> fetchCoordinates(LocationInput locationInput);
}
