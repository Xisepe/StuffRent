package com.unnamedteam.stuffrent.controller;

import com.unnamedteam.stuffrent.exeptions.AdvertIsRentedException;
import com.unnamedteam.stuffrent.exeptions.NotEnoughMoneyOnBalanceException;
import com.unnamedteam.stuffrent.exeptions.SelfRentException;
import com.unnamedteam.stuffrent.filters.jwt.JwtProvider;
import com.unnamedteam.stuffrent.model.client.advert.Advert;
import com.unnamedteam.stuffrent.model.client.user.CashAccount;
import com.unnamedteam.stuffrent.model.client.user.Users;
import com.unnamedteam.stuffrent.service.AdvertService;
import com.unnamedteam.stuffrent.service.BankOperationService;
import com.unnamedteam.stuffrent.service.CashAccountService;
import com.unnamedteam.stuffrent.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import java.time.LocalDate;

import static com.unnamedteam.stuffrent.constants.SecurityConstants.HEADER_STRING;
import static com.unnamedteam.stuffrent.constants.SecurityConstants.TOKEN_PREFIX;
import static java.time.temporal.ChronoUnit.DAYS;

@RestController
@AllArgsConstructor
public class RentController {

    private JwtProvider jwtProvider;
    private AdvertService advertService;
    private UserService userService;
    private CashAccountService cashAccountService;

    @PostMapping("/user/{userId}/advert/{advertId}/rentStart")
    ResponseEntity<String> startRentAdvert(
            @RequestHeader(HEADER_STRING) String token,
            @PathVariable Long userId,
            @PathVariable Long advertId,
            @RequestParam @Min(1) @Max(91) int days
    ) {
        String username = jwtProvider.getUsernameFromToken(token.substring(TOKEN_PREFIX.length()));
        Users user = userService.findUserByUsernameWithCheck(username);
        checkSelfRent(user.getId(), userId);
        Advert advert = advertService.getAdvertById(advertId);
        advertService.checkAdvertOnRent(advert);
        CashAccount fromAccount = cashAccountService.findCashAccountByUserId(user.getId());
        CashAccount toAccount = cashAccountService.findCashAccountByUserId(userId);
        LocalDate now = LocalDate.now();
        advert.getAdvertData().setStartRent(now);
        advert.getAdvertData().setEndRent(now.plusDays(days));
        cashAccountService.transferRentBegin(fromAccount, toAccount, calculateRentCost(advert, days));
        advert.setRented(true);
        advert.setRentedById(user.getId());
        return new ResponseEntity<>("Аренда успешно начата", HttpStatus.OK);
    }

    @PostMapping("/user/{userId}/advert/{advertId}/rentEnd")
    ResponseEntity<String> endRentAdvert(
            @RequestHeader(HEADER_STRING) String token,
            @PathVariable Long userId,
            @PathVariable Long advertId
    ) {
        String username = jwtProvider.getUsernameFromToken(token.substring(TOKEN_PREFIX.length()));
        Users user = userService.findUserByUsernameWithCheck(username);
        checkSelfRent(user.getId(), userId);
        Advert advert = advertService.getAdvertById(advertId);
        advertService.checkAdvertOnRent(advert);
        CashAccount fromAccount = cashAccountService.findCashAccountByUserId(user.getId());
        CashAccount toAccount = cashAccountService.findCashAccountByUserId(userId);
        int days = (int)DAYS.between(
                advert.getAdvertData().getStartRent(),
                advert.getAdvertData().getEndRent());
        cashAccountService.transferRentEnd(fromAccount, toAccount, calculateRentCost(advert, days));
        advert.setRented(false);
        advert.setRentedById(null);
        return new ResponseEntity<>("Аренда успешно закончена", HttpStatus.OK);
    }

    private void checkSelfRent(Long selfId, Long userId) {
        if (selfId.equals(userId)) {
            throw new SelfRentException("Вы не можете арендовать вещь у себя");
        }
    }
    private int calculateRentCost(Advert advert, int term) {
        return advert.getAdvertData().getPrice() * term;
    }
}
