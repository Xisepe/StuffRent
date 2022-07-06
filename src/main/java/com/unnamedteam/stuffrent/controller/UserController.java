package com.unnamedteam.stuffrent.controller;

import com.unnamedteam.stuffrent.model.User;
import com.unnamedteam.stuffrent.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
public class UserController {
    private UserService service;

    @GetMapping(path = "/users",produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<User> getAll() {
        List<User>r = new ArrayList<User>();
        r.add(service.getUserByUsername("xisepe"));
        return r;
    }

    @PostMapping("/users")
    User newUser(@RequestBody User user) {
        return service.saveUser(user);
    }

}
