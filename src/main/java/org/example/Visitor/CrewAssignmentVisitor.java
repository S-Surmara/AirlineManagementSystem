package org.example.Visitor;

import org.example.Entity.Admin;
import org.example.Entity.Crew;
import org.example.Entity.Flight;
import org.example.Entity.Passenger;

public class CrewAssignmentVisitor implements UserVisitor {

    private final Flight flight;

    public CrewAssignmentVisitor(Flight flight) {
        this.flight = flight;
    }

    @Override
    public void visit(Crew crew) {
        // ✅ Only Crew reaches here — no instanceof, no cast
        flight.getCrewList().add(crew);
        System.out.println("Crew " + crew.getName() + " added to flight "
                + flight.getFlightName());
    }

    @Override
    public void visit(Admin admin) {
        // ❌ Admin cannot be assigned as crew
        throw new RuntimeException("Admin cannot be assigned as crew.");
    }

    @Override
    public void visit(Passenger passenger) {
        // ❌ Passenger cannot be assigned as crew
        throw new RuntimeException("Passenger cannot be assigned as crew.");
    }
}
