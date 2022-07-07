package com.unnamedteam.stuffrent.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {



    @GetMapping("/admin/**")
    public String getAdmin() {
        return "Hi admin";
    }

    @GetMapping("/user/**")
    public String getUser(Authentication authentication){

        return authentication.getName();
    }
}

