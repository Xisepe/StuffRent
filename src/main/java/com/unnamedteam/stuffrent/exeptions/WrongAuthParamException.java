package com.unnamedteam.stuffrent.exeptions;

public class WrongAuthParamException extends RuntimeException{
    public WrongAuthParamException() {
        super("Wrong username or password, please try again");
    }
}
