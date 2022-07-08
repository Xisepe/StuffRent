package com.unnamedteam.stuffrent.controller;

import com.unnamedteam.stuffrent.constants.SecurityConstants;
import com.unnamedteam.stuffrent.exeptions.PhotoUploadException;
import com.unnamedteam.stuffrent.filters.jwt.JwtProvider;
import com.unnamedteam.stuffrent.model.client.DTO.StuffDTO;
import com.unnamedteam.stuffrent.model.client.Stuff;
import com.unnamedteam.stuffrent.service.StuffService;
import com.unnamedteam.stuffrent.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

import static com.unnamedteam.stuffrent.constants.SecurityConstants.HEADER_STRING;
import static com.unnamedteam.stuffrent.constants.SecurityConstants.TOKEN_PREFIX;

import java.awt.*;
import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
public class StuffController {

    private StuffService stuffService;
    private UserService userService;
    private JwtProvider jwtProvider;

    @GetMapping("/user/stuff")
    public List<Stuff> getAllUserStuff(@RequestHeader(HEADER_STRING)String token) {
        String username = jwtProvider.getUsernameFromToken(token.substring(TOKEN_PREFIX.length()));
        Long id = userService.findUserByUsername(username).getId();
        return stuffService.findAllUserStuffByUserId(id);
    }

    @PostMapping(value = "/user/stuff", consumes = {MediaType.APPLICATION_JSON_VALUE,
    MediaType.MULTIPART_FORM_DATA_VALUE})
    public String saveAllUserStuff(@RequestHeader(HEADER_STRING) String token,
                                   @RequestParam("file") MultipartFile multipartFile,
                                   @RequestParam("data")String data) {
        String username = jwtProvider.getUsernameFromToken(token.substring(TOKEN_PREFIX.length()));
        Long userId = userService.findUserByUsername(username).getId();
        Stuff stuff = new Stuff();
        stuff.setUserId(userId);
        try{
            byte[]bytes = multipartFile.getBytes();
            stuffService.saveStuff(stuff, bytes, data);
            return "OK";
        }catch (IOException e) {
           e.printStackTrace();
           throw new PhotoUploadException();
        }
    }

}
