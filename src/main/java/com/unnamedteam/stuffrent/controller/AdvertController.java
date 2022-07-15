package com.unnamedteam.stuffrent.controller;

import com.unnamedteam.stuffrent.constants.SecurityConstants;
import com.unnamedteam.stuffrent.exeptions.UserNotFoundException;
import com.unnamedteam.stuffrent.filters.jwt.JwtProvider;
import com.unnamedteam.stuffrent.model.client.DTO.AdvertDTO;
import com.unnamedteam.stuffrent.model.client.DTO.ResponseAdvert;
import com.unnamedteam.stuffrent.model.client.advert.Advert;
import com.unnamedteam.stuffrent.model.client.advert.Category;
import com.unnamedteam.stuffrent.model.client.user.Users;
import com.unnamedteam.stuffrent.service.AdvertService;
import com.unnamedteam.stuffrent.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
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


    @GetMapping("/advert")
    public ResponseEntity<List<ResponseAdvert>> getAllAdverts(
            @RequestParam(name = "category", defaultValue = "ALL", required = false) String category,
            @RequestParam(name = "name", defaultValue = "ALL", required = false) String name,
            @RequestParam(name = "isRented", defaultValue = "ALL", required = false) String isRented
    ) {
        List<Advert> adverts = advertService.findAll();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION)
                .body(adverts.stream()
                        .filter(e -> category.equals("ALL")
                                ||e.getAdvertData().getCategory().toString().equals(category))
                        .filter(e -> name.equals("ALL")
                                ||e.getAdvertData().getName().contains(name))
                        .filter(e -> isRented.equals("ALL")
                        || e.isRented() == isRented.equals("true"))
                        .map(e -> advertService.convertAdvertToResponseAdvert(e))
                        .collect(Collectors.toList()));
    }

    @GetMapping("/user/{id}/advert")
    public ResponseEntity<List<ResponseAdvert>> getAllAdvertsByUserId(
            @PathVariable Long id
    ) {
        userService.findUserById(id);
        List<Advert> adverts = advertService.findAllAdvertsByOwnerId(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION)
                .body(adverts.stream()
                        .map(e -> advertService.convertAdvertToResponseAdvert(e))
                        .collect(Collectors.toList()));
    }

    @GetMapping("/user/{userId}/advert/{advertId}")
    public ResponseEntity<ResponseAdvert> getAdvertById(
            @PathVariable Long userId,
            @PathVariable Long advertId
    ) {
        userService.findUserById(userId);
        Advert advert = advertService.getAdvertById(advertId);
        advertService.checkAdvertOnExistence(advert);
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
        String username = jwtProvider.getUsernameFromToken(token.substring(TOKEN_PREFIX.length()));
        Users user = userService.findUserByUsernameWithCheck(username);
        userService.checkNumberOfAdverts(user);
        if (!user.getId().equals(id)) {
            throw new AccessDeniedException("Действие запрещено");
        }
        advertService.saveAdvert(advertDTO, user, multipartFile);
        return new ResponseEntity<>("Успешно сохранено", HttpStatus.OK);
    }

}
