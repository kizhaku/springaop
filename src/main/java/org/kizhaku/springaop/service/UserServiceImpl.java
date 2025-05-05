package org.kizhaku.springaop.service;

import org.kizhaku.springaop.exception.UserNotFoundException;
import org.kizhaku.springaop.model.User;
import org.kizhaku.springaop.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User findUserById(String id) throws UserNotFoundException {
        Optional<User> user = userRepo.getUserById(id);

        return user.orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public User addUser(User user) {
        return userRepo.addUser(user);
    }
}
