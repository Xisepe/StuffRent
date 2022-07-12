package com.unnamedteam.stuffrent.controller;

import com.unnamedteam.stuffrent.filters.jwt.JwtProvider;
import com.unnamedteam.stuffrent.model.client.user.CashAccount;
import com.unnamedteam.stuffrent.model.client.user.Users;
import com.unnamedteam.stuffrent.service.CashAccountService;
import com.unnamedteam.stuffrent.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.unnamedteam.stuffrent.constants.SecurityConstants.HEADER_STRING;
import static com.unnamedteam.stuffrent.constants.SecurityConstants.TOKEN_PREFIX;

@RestController
@AllArgsConstructor
public class BalanceController {

    private JwtProvider jwtProvider;
    private UserService userService;
    private CashAccountService cashAccountService;

    @GetMapping("/user/balance")
    ResponseEntity<Integer> getBalance(@RequestHeader(HEADER_STRING) String token) {
        jwtProvider.validate(token);
        Users user = userService.findUserByUsername(
                token.substring(TOKEN_PREFIX.length()));
        int balance = cashAccountService.getBalance(user.getId());
        return ResponseEntity.ok(balance);
    }
    @GetMapping("/user/balance/deposit")
    ResponseEntity<Void> getDeposit(@RequestHeader(HEADER_STRING) String token) {
        jwtProvider.validate(token);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/user/balance/deposit")
    ResponseEntity<String> deposit(@RequestHeader(HEADER_STRING) String token,
                                   @RequestParam("amount") Integer amount) {
        jwtProvider.validate(token);
        Users user = userService.findUserByUsername(
                token.substring(TOKEN_PREFIX.length()));
        CashAccount cashAccount = cashAccountService.findCashAccountByUserId(user.getId());
        cashAccountService.deposit(cashAccount, amount);
        return ResponseEntity.ok("Успешно пополнено");
    }

    @GetMapping("/user/balance/withdraw")
    ResponseEntity<Void> getWithdraw(@RequestHeader(HEADER_STRING) String token) {
        jwtProvider.validate(token);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/user/balance/withdraw")
    ResponseEntity<String> withdraw(@RequestHeader(HEADER_STRING) String token,
                                   @RequestParam("amount") Integer amount) {
        jwtProvider.validate(token);
        Users user = userService.findUserByUsername(
                token.substring(TOKEN_PREFIX.length()));
        CashAccount cashAccount = cashAccountService.findCashAccountByUserId(user.getId());
        cashAccountService.withdraw(cashAccount, amount);
        return ResponseEntity.ok("Успешно выведено");
    }
}
