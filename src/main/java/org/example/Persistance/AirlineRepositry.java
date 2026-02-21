package org.example.Persistance;

import org.example.Entity.Flight;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AirlineRepositry {

    // FIXED: Raw Map → typed, initialized with ConcurrentHashMap for thread safety
    private final Map<String, Flight> flightMap = new ConcurrentHashMap<>();

    public void addFlight(Flight flight) {
        flightMap.put(flight.getFlightId(), flight);
    }

    public Flight getFlight(String flightId) {
        return flightMap.get(flightId);
    }

    public List<Flight> getAllFlights() {
        return new ArrayList<>(flightMap.values());
    }
}
