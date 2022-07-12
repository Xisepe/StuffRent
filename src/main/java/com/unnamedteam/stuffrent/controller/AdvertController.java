package com.unnamedteam.stuffrent.controller;

import com.unnamedteam.stuffrent.constants.SecurityConstants;
import com.unnamedteam.stuffrent.exeptions.UserNotFoundException;
import com.unnamedteam.stuffrent.filters.jwt.JwtProvider;
import com.unnamedteam.stuffrent.model.client.DTO.AdvertDTO;
import com.unnamedteam.stuffrent.model.client.DTO.ResponseAdvert;
import com.unnamedteam.stuffrent.model.client.advert.Advert;
import com.unnamedteam.stuffrent.model.client.user.Users;
import com.unnamedteam.stuffrent.service.AdvertService;
import com.unnamedteam.stuffrent.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

import static com.unnamedteam.stuffrent.constants.SecurityConstants.HEADER_STRING;
import static com.unnamedteam.stuffrent.constants.SecurityConstants.TOKEN_PREFIX;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class AdvertController {

    private UserService userService;
    private AdvertService advertService;
    private JwtProvider jwtProvider;

    @GetMapping("/user/{id}/advert")
    public ResponseEntity<List<ResponseAdvert>> getAllAdverts(
            @RequestHeader(HEADER_STRING) String token,
            @PathVariable Long id
    ) {
        jwtProvider.validate(token);
        userService.checkUser(userService.findUserById(id));
        List<Advert> adverts = advertService.findAllAdvertsByOwnerId(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION)
                .body(adverts.stream()
                        .map(e -> advertService.convertAdvertToResponseAdvert(e))
                        .collect(Collectors.toList()));
    }

    @GetMapping("/user/{userId}/advert/{advertId}")
    public ResponseEntity<ResponseAdvert> getAdvertById(
            @RequestHeader(HEADER_STRING) String token,
            @PathVariable Long userId,
            @PathVariable Long advertId
    ) {
        jwtProvider.validate(token);
        userService.checkUser(userService.findUserById(userId));
        Advert advert = advertService.getAdvertById(advertId);
        advertService.checkAdvert(advert);
        ResponseAdvert response = advertService.convertAdvertToResponseAdvert(advert);
        return ResponseEntity
                .ok()
                .body(response);

    }

    @PostMapping(value = "/user/{id}/advert", consumes = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> saveUserAdvert(
            @RequestHeader(HEADER_STRING) String token,
            @RequestPart("file") MultipartFile multipartFile,
            @RequestPart("json") @Valid AdvertDTO advertDTO,
            @PathVariable Long id
    ) {
        jwtProvider.validate(token);
        String username = jwtProvider.getUsernameFromToken(token.substring(TOKEN_PREFIX.length()));
        Users user = userService.findUserByUsername(username);
        userService.checkUser(user);
        userService.checkNumberOfAdverts(user);
        if (!user.getId().equals(id)) {
            throw new AccessDeniedException("Access denied");
        }
        advertService.saveAdvert(advertDTO, user, multipartFile);
        return new ResponseEntity<>("successfully saved", HttpStatus.OK);
    }

}
