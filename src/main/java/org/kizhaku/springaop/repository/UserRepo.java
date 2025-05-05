package org.kizhaku.springaop.repository;

import org.kizhaku.springaop.model.User;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class UserRepo {

    private static final Map<String, User> users = new ConcurrentHashMap<>();

    public User addUser(User user) {
        return users.put(user.getId(), user);
    }

    public Optional<User> getUserById(String id) {
        User user = users.get(id);
        return user != null ? Optional.of(users.get(id)) : Optional.empty();
    }
}
