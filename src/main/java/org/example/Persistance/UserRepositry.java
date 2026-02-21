package org.example.Persistance;

import org.example.Entity.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserRepositry {

    // FIXED: Raw Map → typed, initialized
    private final Map<String, User> userMap = new ConcurrentHashMap<>();

    public void addUser(User user) {
        userMap.put(user.getUserId(), user);
    }

    public User getUser(String userId) {
        return userMap.get(userId);
    }
}
