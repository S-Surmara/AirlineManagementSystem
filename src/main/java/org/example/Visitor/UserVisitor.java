package org.example.Visitor;

import org.example.Entity.Admin;
import org.example.Entity.Crew;
import org.example.Entity.Passenger;

// One visit() method per concrete User subtype
public interface UserVisitor {
    void visit(Admin admin);
    void visit(Crew crew);
    void visit(Passenger passenger);
}
