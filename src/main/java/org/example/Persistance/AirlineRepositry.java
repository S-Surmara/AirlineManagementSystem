package org.example.Persistance;

import org.example.Entity.Flight;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AirlineRepositry {
    Map<String,Flight> flightMap;

    public void addFlight(Flight flight){
        flightMap.put(flight.getFlightId(),flight);
    }

    public Flight getFlight(String flightId){
        return flightMap.get(flightId);
    }

    public List<Flight> getAllFlights(){
        return new ArrayList<>(flightMap.values());
    }
}
