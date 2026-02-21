package org.example.Services;

import org.example.Entity.Flight;
import org.example.Persistance.AirlineRepositry;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class SearchService {

    private final AirlineRepositry airlineRepositry;

    public SearchService(AirlineRepositry airlineRepositry) {
        this.airlineRepositry = airlineRepositry;
    }

    public List<Flight> search(String source, String destination, LocalDate date) {
        return airlineRepositry.getAllFlights()
                .stream()
                .filter(flight ->
                        // FIXED: == → equalsIgnoreCase for case-insensitive string comparison
                        flight.getSource().equalsIgnoreCase(source)
                                && flight.getDestination().equalsIgnoreCase(destination)
                                // FIXED: == → .equals() for LocalDate comparison
                                && flight.getDepartureDateAndTime().toLocalDate().equals(date))
                .collect(Collectors.toList());
    }
}
