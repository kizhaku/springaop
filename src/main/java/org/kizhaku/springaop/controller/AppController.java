package org.kizhaku.springaop.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.kizhaku.springaop.model.User;
import org.kizhaku.springaop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("api/v1")
@Tag(name="App Controller")
public class AppController {

    private UserService userService;

    public AppController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("user/{id}")
    public ResponseEntity<User> findUserById(@PathVariable("id") String id, HttpSession httpSession,
                                             HttpServletRequest req) {
        System.out.println("User session is: " +httpSession.getAttribute("userSessionName"));
        System.out.println(httpSession.getId());
        System.out.println("---------------Cookies-------------");
        Arrays.stream(req.getCookies()).forEach(x -> System.out.println(x.getName() +" : "+ x.getValue()));

        return new ResponseEntity<>(userService.findUserById(id), HttpStatus.FOUND);
    }

    @PostMapping("user")
    public ResponseEntity<User> addUser(@RequestBody User user, HttpSession httpSession) {
        String s = "Session" +user.getFirstName();
        httpSession.setAttribute("userSessionName", s);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Set-Cookie", "token=abc123; HttpOnly; Path=/; Max-Age=3600");

        userService.addUser(user);

        return new ResponseEntity<>(user, httpHeaders, HttpStatus.CREATED);
    }
}
