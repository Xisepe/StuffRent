package com.unnamedteam.stuffrent.controller;

import com.unnamedteam.stuffrent.exeptions.InvalidTokenException;
import com.unnamedteam.stuffrent.exeptions.UserNotFoundException;
import com.unnamedteam.stuffrent.filters.jwt.JwtProvider;
import com.unnamedteam.stuffrent.model.client.DTO.UserDataDTO;
import com.unnamedteam.stuffrent.model.client.user.UserData;
import com.unnamedteam.stuffrent.model.client.user.Users;
import com.unnamedteam.stuffrent.model.client.mapper.UserDataDTOMapper;
import com.unnamedteam.stuffrent.service.UserDataService;
import com.unnamedteam.stuffrent.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
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

    @GetMapping("/user/{id}/personal")
    public UserDataDTO getUserData(
            @PathVariable Long id,
            @RequestHeader(HEADER_STRING) String token
    ) {
        if (!jwtProvider.validateToken(token))
            throw new InvalidTokenException("Invalid token");
        Users user = userService.findUserById(id);
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }
        UserData userData = userDataService.findUserDataByUserId(user.getId());
        return UserDataDTOMapper.getDTO(userData);
    }

    @PostMapping("/user/{id}/personal")
    public UserDataDTO updateUserData(@RequestHeader(name = HEADER_STRING) String token,
                                      @PathVariable Long id,
                                      @RequestBody @Valid UserDataDTO userDataDTO) {
        Users user = userService.findUserByUsername(
                jwtProvider.getUsernameFromToken(
                        token.substring(TOKEN_PREFIX.length())));
        if (!user.getId().equals(id)) {
            throw new AccessDeniedException("Operation denied");
        }
        UserData userData = userDataService.findUserDataByUserId(user.getId());
        userDataService.saveUserData(userData, user.getId(), userDataDTO);
        return userDataDTO;
    }

}
