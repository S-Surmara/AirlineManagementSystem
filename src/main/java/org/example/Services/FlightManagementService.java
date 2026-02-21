package org.example.Services;

import org.example.Entity.Crew;
import org.example.Entity.Flight;
import org.example.Entity.FlightDetails;
import org.example.Entity.User;
import org.example.Persistance.AirlineRepositry;
import org.example.Persistance.UserRepositry;
import org.example.Visitor.CrewAssignmentVisitor;

public class FlightManagementService {

    private final AirlineRepositry airlineRepositry;
    private final UserRepositry userRepositry;

    public FlightManagementService(AirlineRepositry airlineRepositry, UserRepositry userRepositry) {
        this.airlineRepositry = airlineRepositry;
        // FIXED: userRepositry was not assigned in original constructor
        this.userRepositry = userRepositry;
    }

    public void addFlight(Flight flight) {
        airlineRepositry.addFlight(flight);
    }

    public void removeCrew(String flightId, String crewId) {
        Flight flight = airlineRepositry.getFlight(flightId);
        if (flight == null) throw new RuntimeException("Flight not found: " + flightId);

        // FIXED: was using == for string comparison + removing wrong object from list
        Crew crewToRemove = flight.getCrewList().stream()
                .filter(crew -> crew.getUserId().equals(crewId))
                .findFirst()
                .orElse(null);

        if (crewToRemove == null) {
            System.out.println("Crew member not found on this flight.");
            return;
        }

        flight.getCrewList().remove(crewToRemove);
        System.out.println("Crew removed successfully.");
    }

    public void addCrew(String flightId, String crewId) {
        Flight flight = airlineRepositry.getFlight(flightId);
        if (flight == null) throw new RuntimeException("Flight not found: " + flightId);

        User user = userRepositry.getUser(crewId);
        if (user == null) throw new RuntimeException("User not found: " + crewId);

        // No instanceof. No cast.
        // user.accept() dispatches to the correct visit() method automatically
        user.accept(new CrewAssignmentVisitor(flight));
    }


    public void modifyFlightDetails(String flightId, FlightDetails flightDetails) {
        Flight flight = airlineRepositry.getFlight(flightId);
        if (flight == null) throw new RuntimeException("Flight not found: " + flightId);

        flight.setSource(flightDetails.getSource());
        flight.setDestination(flightDetails.getDestination());
        flight.setDepartureDateAndTime(flightDetails.getDepartureTime());
        flight.setSeatmap(flightDetails.getSeatmap());
        flight.setCapacity(flightDetails.getCapacity());

        airlineRepositry.addFlight(flight);
        System.out.println("Flight details updated successfully.");
    }
}
