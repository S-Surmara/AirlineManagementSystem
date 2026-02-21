package org.example.Entity;

import org.example.Enums.Sex;
import org.example.Visitor.UserVisitor;

// Admin.java
public class Admin extends User {
    public Admin(String userId, String name, Sex sex) {
        super(userId,name,sex);
    }
    @Override
    public void accept(UserVisitor visitor) {
        visitor.visit(this);  // calls visit(Admin admin)
    }
}

