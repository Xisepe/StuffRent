package com.unnamedteam.stuffrent.exeptions;
import static com.unnamedteam.stuffrent.constants.ExceptionMessages.usernameIsUsedExceptionMessage;
public class UsernameIsUsedException extends RuntimeException{
    public UsernameIsUsedException() {
        super(usernameIsUsedExceptionMessage);
    }
}
