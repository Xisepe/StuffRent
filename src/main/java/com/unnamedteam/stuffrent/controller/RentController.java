package com.unnamedteam.stuffrent.controller;

import com.unnamedteam.stuffrent.filters.jwt.JwtProvider;
import com.unnamedteam.stuffrent.model.client.advert.Advert;
import com.unnamedteam.stuffrent.model.client.user.Users;
import com.unnamedteam.stuffrent.service.AdvertService;
import com.unnamedteam.stuffrent.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

import static com.unnamedteam.stuffrent.constants.SecurityConstants.HEADER_STRING;
import static com.unnamedteam.stuffrent.constants.SecurityConstants.TOKEN_PREFIX;

@RestController
@AllArgsConstructor
public class RentController {

    private JwtProvider jwtProvider;
    private AdvertService advertService;
    private UserService userService;

//    @PostMapping("/user/{userId}/advert/{advertId}/rent")
//    ResponseEntity<String> rentAdvert(
//            @RequestHeader(HEADER_STRING) String token,
//            @PathVariable Long userId,
//            @PathVariable Long advertId
//    ) {
//        jwtProvider.validate(token);
//        String username = jwtProvider.getUsernameFromToken(token.substring(TOKEN_PREFIX.length()));
//        Users user = userService.findUserByUsername(username);
//        if (user.getId().equals(userId)) {
//            throw new AccessDeniedException("Access denied");
//        }
//
//        Advert advert = advertService.getAdvertById(advertId);
//        advertService.checkAdvert(advert);
//    }
}
