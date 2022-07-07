package com.unnamedteam.stuffrent.exeptions;

public class UsernameIsUsedException extends RuntimeException{
    public UsernameIsUsedException() {
        super("User with this username already exists! Please, try another one");
    }
}
