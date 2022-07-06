package com.unnamedteam.stuffrent.controller;

import com.unnamedteam.stuffrent.model.User;
import com.unnamedteam.stuffrent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    private UserService service;
    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping(path = "/users",produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<User> getAll() {
        return service.getAllUsers();
    }
}
