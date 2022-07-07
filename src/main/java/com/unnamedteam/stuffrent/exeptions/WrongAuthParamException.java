package com.unnamedteam.stuffrent.exeptions;

import static com.unnamedteam.stuffrent.constants.ExceptionMessages.wrongAuthParamExceptionMessage;

public class WrongAuthParamException extends RuntimeException{
    public WrongAuthParamException() {
        super(wrongAuthParamExceptionMessage);
    }
}
