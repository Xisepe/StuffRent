package com.unnamedteam.stuffrent.controller;

import com.unnamedteam.stuffrent.filters.jwt.JwtProvider;
import com.unnamedteam.stuffrent.model.client.UserData;
import com.unnamedteam.stuffrent.model.client.UserDataDTO;
import com.unnamedteam.stuffrent.model.client.Users;
import com.unnamedteam.stuffrent.model.client.mapper.UserDataDTOMapper;
import com.unnamedteam.stuffrent.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.unnamedteam.stuffrent.constants.SecurityConstants.HEADER_STRING;

@RestController
@AllArgsConstructor
public class UserDataController {

    private UserService userService;
    private JwtProvider jwtProvider;

    @GetMapping("/user/data")
    public UserDataDTO getUserData(@RequestHeader(name = HEADER_STRING) String token) {
        Users user = userService.findUserByUsername(jwtProvider.getUsernameFromToken(token));
        return UserDataDTOMapper.getDTO(user);
    }
    @PostMapping("/user/data")
    public UserDataDTO updateUserData(@RequestHeader(name = HEADER_STRING) String token,
                                      @RequestBody UserDataDTO userDataDTO) {
        Users user = userService.findUserByUsername(jwtProvider.getUsernameFromToken(token));
        userService.updateUserDataFromDTO(user, userDataDTO);
        return userDataDTO;
    }

}
