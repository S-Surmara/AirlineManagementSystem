package org.example.Entity;

import org.example.Enums.Sex;
import org.example.Visitor.UserVisitor;

// FIXED: Made abstract — User is only ever Admin, Crew, or Passenger; never raw User
public abstract class User {

    private String userId;
    private String name;
    private Sex sex;

    public User(String userId, String name, Sex sex) {
        this.userId = userId;
        this.name = name;
        this.sex = sex;
    }

    public String getUserId() { return userId; }
    public String getName() { return name; }
    public Sex getSex() { return sex; }

    public abstract void accept(UserVisitor visitor);
}
