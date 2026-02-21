package org.example.Persistance;


import org.example.Entity.User;

import java.util.Map;

public class UserRepositry {
    Map<String, User> userMap;

    public void addUser(User user){
        userMap.put(user.getUserId(),user);
    }

    public User getUser(String userId){
        return userMap.get(userId);
    }
}
