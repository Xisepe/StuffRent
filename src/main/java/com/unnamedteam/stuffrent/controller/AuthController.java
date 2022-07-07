package com.unnamedteam.stuffrent.controller;

import com.unnamedteam.stuffrent.controller.model.AuthRequest;
import com.unnamedteam.stuffrent.controller.model.AuthResponse;
import com.unnamedteam.stuffrent.controller.model.RegistrationRequest;
import com.unnamedteam.stuffrent.exeptions.WrongAuthParamException;
import com.unnamedteam.stuffrent.filters.jwt.JwtProvider;
import com.unnamedteam.stuffrent.model.Users;
import com.unnamedteam.stuffrent.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.unnamedteam.stuffrent.exeptions.UsernameIsUsedException;

import javax.validation.Valid;


@RestController
@AllArgsConstructor
public class AuthController {
    private UserService userService;
    private JwtProvider jwtProvider;



    @Operation(summary = "add user to database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "User successfully added to database",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Users.class))}),
            @ApiResponse(responseCode = "400",
                    description = " Bad input",
                    content = @Content),
            @ApiResponse(responseCode = "409",
            description = "User with same username already exists",
            content = @Content)
    })
    @PostMapping("/register")
    public Users registerUser(@RequestBody @Valid RegistrationRequest request) {
        if (userService.findUserByUsername(request.getUsername()) != null) {
            throw new UsernameIsUsedException();
        }
        Users user = new Users();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        userService.saveUser(user);
        return user;
    }

    @Operation(summary = "authenticate")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Successfully got token",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AuthResponse.class))}),
            @ApiResponse(responseCode = "400",
                    description = " Bad input",
                    content = @Content)
    })
    @PostMapping("/auth")
    public AuthResponse auth(@RequestBody  @Valid AuthRequest request) {
        Users users = userService.findUserByUsernameAndPassword(request.getUsername(),request.getPassword());
        if (users == null) {
            throw new WrongAuthParamException();
        }
        String token = jwtProvider.generateToken(users.getUsername());
        return new AuthResponse(token);
    }
}
