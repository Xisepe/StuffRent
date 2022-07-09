package com.unnamedteam.stuffrent.exeptions.advices;

import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SizeLimitExceededAdvice {

    @Autowired
    private Environment environment;

    @ExceptionHandler(SizeLimitExceededException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<String> sizeLimitExceededExceptionHandler(SizeLimitExceededException e) {
        String sizeLimit = environment.getProperty("spring.servlet.multipart.max-file-size");
        return new ResponseEntity<>(String.format("Размер не может превышать %s",sizeLimit ),
                HttpStatus.BAD_REQUEST);
    }
}
