package org.example.Entity;

import org.example.Enums.Sex;

public class User {
    String userId;
    String  name;
    Sex sex;

    public User(String userId,String name,Sex sex){
        this.userId = userId;
        this.name = name;
        this.sex = sex;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public Sex getSex() {
        return sex;
    }
}
