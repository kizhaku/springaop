package org.kizhaku.springaop.service;

import org.kizhaku.springaop.model.User;

public interface UserService {

    public User findUserById(String id);
    public User addUser(User user);
}
