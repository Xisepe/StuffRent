package com.unnamedteam.stuffrent.controller;

import com.unnamedteam.stuffrent.controller.model.AuthRequest;
import com.unnamedteam.stuffrent.controller.model.AuthResponse;
import com.unnamedteam.stuffrent.controller.model.RegistrationRequest;
import com.unnamedteam.stuffrent.filters.jwt.JwtProvider;
import com.unnamedteam.stuffrent.model.Users;
import com.unnamedteam.stuffrent.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class AuthController {
    private UserService userService;
    private JwtProvider jwtProvider;

    @PostMapping("/register")
    public String registerUser(@RequestBody @Valid RegistrationRequest request) {
        Users user = new Users();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        userService.saveUser(user);
        return "OK";
    }

    @PostMapping("/auth")
    public AuthResponse auth(@RequestBody AuthRequest request) {
        Users users = userService.findUserByUsernameAndPassword(request.getUsername(),request.getPassword());
        String token = jwtProvider.generateToken(users.getUsername());
        return new AuthResponse(token);
    }
}
