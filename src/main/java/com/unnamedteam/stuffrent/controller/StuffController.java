package com.unnamedteam.stuffrent.controller;

import com.unnamedteam.stuffrent.constants.SecurityConstants;
import com.unnamedteam.stuffrent.filters.jwt.JwtProvider;
import com.unnamedteam.stuffrent.model.client.Stuff;
import com.unnamedteam.stuffrent.service.StuffService;
import com.unnamedteam.stuffrent.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static com.unnamedteam.stuffrent.constants.SecurityConstants.HEADER_STRING;
import static com.unnamedteam.stuffrent.constants.SecurityConstants.TOKEN_PREFIX;

import java.util.List;

@RestController
@AllArgsConstructor
public class StuffController {

    private StuffService stuffService;
    private UserService userService;
    private JwtProvider jwtProvider;

    @GetMapping("/user/stuff")
    public List<Stuff> getAllUserStuff(@RequestHeader(HEADER_STRING)String token,
                                       @RequestParam MultipartFile multipartFile) {
        String username = jwtProvider.getUsernameFromToken(token.substring(TOKEN_PREFIX.length()));
        Long id = userService.findUserByUsername(username).getId();
        return stuffService.getAllUserStuffByUserId(id);
    }

}
