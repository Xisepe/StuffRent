package com.unnamedteam.stuffrent.exeptions.advices;

import com.unnamedteam.stuffrent.exeptions.UsernameIsUsedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class UsernameIsUsedAdvice {

    @ExceptionHandler(UsernameIsUsedException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    ResponseEntity<String> usernameIsUsedHandler(UsernameIsUsedException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }
}
