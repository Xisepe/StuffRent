package com.unnamedteam.stuffrent.controller;

import com.unnamedteam.stuffrent.filters.jwt.JwtProvider;
import com.unnamedteam.stuffrent.model.Users;
import com.unnamedteam.stuffrent.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import static com.unnamedteam.stuffrent.model.SecurityConstants.HEADER_STRING;


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

