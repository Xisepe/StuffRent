package com.unnamedteam.stuffrent.exeptions.advices;

import com.unnamedteam.stuffrent.exeptions.AdvertIsRentedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdvertIsRentedAdvice {
    @ExceptionHandler(AdvertIsRentedException.class)
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    ResponseEntity<String> advertIsRentedExceptionHandler(AdvertIsRentedException e) {
        return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }
}
