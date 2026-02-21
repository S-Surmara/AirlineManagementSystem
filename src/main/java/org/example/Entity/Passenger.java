package org.example.Entity;

import org.example.Enums.Sex;
import org.example.Visitor.UserVisitor;

// ADDED: Passenger was missing entirely from codebase
public class Passenger extends User {

    private String email;
    private String phone;

    public Passenger(String passengerId, String name, Sex sex, String email, String phone) {
        super(passengerId, name, sex);
        this.email = email;
        this.phone = phone;
    }

    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    @Override
    public void accept(UserVisitor visitor) {
        visitor.visit(this);  // calls visit(Passenger passenger)
    }
}
