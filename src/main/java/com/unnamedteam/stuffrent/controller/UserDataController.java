package com.unnamedteam.stuffrent.controller;

import com.unnamedteam.stuffrent.filters.jwt.JwtProvider;
import com.unnamedteam.stuffrent.model.client.DTO.UserDataDTO;
import com.unnamedteam.stuffrent.model.client.UserData;
import com.unnamedteam.stuffrent.model.client.Users;
import com.unnamedteam.stuffrent.model.client.mapper.UserDataDTOMapper;
import com.unnamedteam.stuffrent.model.client.mapper.UserDataFromDTOMapper;
import com.unnamedteam.stuffrent.service.UserDataService;
import com.unnamedteam.stuffrent.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.unnamedteam.stuffrent.constants.SecurityConstants.HEADER_STRING;
import static com.unnamedteam.stuffrent.constants.SecurityConstants.TOKEN_PREFIX;

@RestController
@AllArgsConstructor
public class UserDataController {

    private UserService userService;
    private UserDataService userDataService;
    private JwtProvider jwtProvider;

    @GetMapping("/user/data")
    public UserDataDTO getUserData(@RequestHeader(name = HEADER_STRING) String token) {
        Users user = userService.findUserByUsername(
                jwtProvider.getUsernameFromToken(
                        token.substring(TOKEN_PREFIX.length())));
        UserData userData = userDataService.findUserDataByUserId(user.getId());
        return UserDataDTOMapper.getDTO(userData);
    }

    @PostMapping("/user/data")
    public UserDataDTO updateUserData(@RequestHeader(name = HEADER_STRING) String token,
                                      @RequestBody @Valid UserDataDTO userDataDTO) {
        Users user = userService.findUserByUsername(
                jwtProvider.getUsernameFromToken(
                        token.substring(TOKEN_PREFIX.length())));
        UserData userData = userDataService.findUserDataByUserId(user.getId());
        userDataService.saveUserData(userData, user.getId(),userDataDTO);
        return userDataDTO;
    }

}
