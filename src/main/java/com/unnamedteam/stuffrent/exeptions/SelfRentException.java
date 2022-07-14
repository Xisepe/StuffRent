package com.unnamedteam.stuffrent.exeptions;

public class SelfRentException extends RuntimeException{
    public SelfRentException(String message) {
        super(message);
    }
}
