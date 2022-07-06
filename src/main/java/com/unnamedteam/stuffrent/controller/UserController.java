package com.unnamedteam.stuffrent.controller;

import com.unnamedteam.stuffrent.model.Users;
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

    @PostMapping(path = "/user", consumes = MediaType.APPLICATION_JSON_VALUE)
    Users newUser(@RequestBody Users users) {
        return service.saveUser(users);
    }

}
