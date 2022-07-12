package com.unnamedteam.stuffrent.exeptions;

public class NumberOfAdvertsExceededException extends RuntimeException{
    public NumberOfAdvertsExceededException(String message) {
        super(message);
    }
}
