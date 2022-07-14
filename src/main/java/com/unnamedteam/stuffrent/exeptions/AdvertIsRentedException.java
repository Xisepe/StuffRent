package com.unnamedteam.stuffrent.exeptions;

public class AdvertIsRentedException extends RuntimeException{
    public AdvertIsRentedException(String message) {
        super(message);
    }
}
