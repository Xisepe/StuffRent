package com.unnamedteam.stuffrent.exeptions.advices;

import com.unnamedteam.stuffrent.exeptions.PhotoUploadException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PhotoUploadAdvice {
    @ExceptionHandler(PhotoUploadException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<String> photoUploadExceptionHandler(PhotoUploadException e) {
        return new ResponseEntity<>("photoUploadException", HttpStatus.BAD_REQUEST);
    }
}
