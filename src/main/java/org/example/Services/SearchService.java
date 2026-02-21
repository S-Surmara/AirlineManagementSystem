package org.example.Services;

import org.example.Entity.Flight;
import org.example.Persistance.AirlineRepositry;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class SearchService {
    AirlineRepositry airlineRepositry;

    public SearchService(AirlineRepositry airlineRepositry){
        this.airlineRepositry = airlineRepositry;
    }
    public List<Flight> search(String source, String destination, LocalDate date){
        return airlineRepositry.getAllFlights()
                .stream()
                .filter((flight) -> flight.getSource() == source
                                && flight.getDestination() == destination
                                && flight.getDepartureDateAndTime().toLocalDate() == date )
                .collect(Collectors.toList());
    }
}
