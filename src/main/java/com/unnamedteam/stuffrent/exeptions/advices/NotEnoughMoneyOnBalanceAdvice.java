package com.unnamedteam.stuffrent.exeptions.advices;

import com.unnamedteam.stuffrent.exeptions.NotEnoughMoneyOnBalanceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class NotEnoughMoneyOnBalanceAdvice {
    @ExceptionHandler(NotEnoughMoneyOnBalanceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<String> notEnoughMoneyOnBalanceExceptionHandler(NotEnoughMoneyOnBalanceException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
