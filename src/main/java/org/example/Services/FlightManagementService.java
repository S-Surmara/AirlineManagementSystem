package org.example.Services;

import org.example.Entity.Crew;
import org.example.Entity.Flight;
import org.example.Entity.FlightDetails;
import org.example.Entity.User;
import org.example.Persistance.AirlineRepositry;
import org.example.Persistance.UserRepositry;

public class FlightManagementService {
    AirlineRepositry airlineRepositry;
    UserRepositry userRepositry;

    public FlightManagementService(AirlineRepositry airlineRepositry,UserRepositry userRepositry) {
        this.airlineRepositry = airlineRepositry;
    }

    public void addFlight(Flight flight){
        airlineRepositry.addFlight(flight);
    }

    public void removeCrew(String flightId,String crewId){
        Flight flight = airlineRepositry.getFlight(flightId);
        boolean exists = flight.getCrewList().stream().anyMatch(crew -> crew.getUserId() == crewId);
        if( !exists ){
            System.out.println("this crew is not part of current plane , please verify again");
            return;
        }
        flight.getCrewList().remove(userRepositry.getUser(crewId));
        System.out.println("removed crew sucessfully");
    }

    public void addCrew(String flightId,String crewId){
        Flight flight = airlineRepositry.getFlight(flightId);
        User user = userRepositry.getUser(crewId);
        flight.getCrewList().add((Crew)user);
        System.out.println("added crew sucessfully");
    }

    public void modifyFlightDetails(String flightId, FlightDetails flightDetails) {
        Flight flight = airlineRepositry.getFlight(flightId);

        if (flight == null) {
            throw new RuntimeException("Flight not found: " + flightId);
        }

        flight.setSource(flightDetails.getSource());
        flight.setDestination(flightDetails.getDestination());
        flight.setDepartureDateAndTime(flightDetails.getDepartureTime());
        flight.setSeatmap(flightDetails.getSeatmap());
        flight.setCapacity(flightDetails.getCapacity());

        airlineRepositry.addFlight(flight);
    }
}
