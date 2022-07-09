package com.unnamedteam.stuffrent.controller;

import com.unnamedteam.stuffrent.filters.jwt.JwtProvider;
import com.unnamedteam.stuffrent.model.client.DTO.AdvertDTO;
import com.unnamedteam.stuffrent.model.client.DTO.ResponseAdvert;
import com.unnamedteam.stuffrent.model.client.advert.Advert;
import com.unnamedteam.stuffrent.service.AdvertService;
import com.unnamedteam.stuffrent.service.StorageService;
import com.unnamedteam.stuffrent.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

import static com.unnamedteam.stuffrent.constants.SecurityConstants.HEADER_STRING;
import static com.unnamedteam.stuffrent.constants.SecurityConstants.TOKEN_PREFIX;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class AdvertController {

    private UserService userService;
    private AdvertService advertService;
    private JwtProvider jwtProvider;

    @GetMapping("/user/advert")
    public ResponseEntity<List<ResponseAdvert>> getAllAdverts(@RequestHeader(HEADER_STRING) String token) {
        String username = jwtProvider.getUsernameFromToken(token.substring(TOKEN_PREFIX.length()));
        Long userId = userService.findUserByUsername(username).getId();
        List<Advert> adverts = advertService.findAllAdvertsByOwnerId(userId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION)
                .body(adverts.stream()
                        .map(e -> advertService.convertAdvertToResponseAdvert(e))
                        .collect(Collectors.toList()));
    }

    @PostMapping(value = "/user/advert/create", consumes = {MediaType.APPLICATION_JSON_VALUE,
    MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> saveAllUserStuff(@RequestHeader(HEADER_STRING) String token,
                                   @RequestPart("file") MultipartFile multipartFile,
                                   @RequestPart("json") @Valid AdvertDTO advertDTO) {
        String username = jwtProvider.getUsernameFromToken(token.substring(TOKEN_PREFIX.length()));
        Long userId = userService.findUserByUsername(username).getId();
        advertService.saveAdvert(advertDTO, userId, multipartFile);
        return new ResponseEntity<>("successfully saved", HttpStatus.OK);
    }

}
