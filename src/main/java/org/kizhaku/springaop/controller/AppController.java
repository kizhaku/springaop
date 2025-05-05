package org.kizhaku.springaop.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.kizhaku.springaop.model.User;
import org.kizhaku.springaop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
@Tag(name="App Controller")
public class AppController {

    private UserService userService;

    public AppController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("user/{id}")
    public ResponseEntity<User> findUserById(@PathVariable("id") String id) {
        return new ResponseEntity<>(userService.findUserById(id), HttpStatus.FOUND);
    }

    @PostMapping("user")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        userService.addUser(user);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
