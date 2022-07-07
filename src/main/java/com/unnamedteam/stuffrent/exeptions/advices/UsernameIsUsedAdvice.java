package com.unnamedteam.stuffrent.exeptions.advices;

import com.unnamedteam.stuffrent.exeptions.UsernameIsUsedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UsernameIsUsedAdvice {
    @ResponseBody
    @ExceptionHandler(UsernameIsUsedException.class)
    @ResponseStatus(HttpStatus.IM_USED)
    String usernameIsUsedHandler(UsernameIsUsedException e) {
        return e.getMessage();
    }
}
