package com.unnamedteam.stuffrent.exeptions;

public class NotEnoughMoneyOnBalanceException extends RuntimeException{
    public NotEnoughMoneyOnBalanceException(String message) {
        super(message);
    }
}
