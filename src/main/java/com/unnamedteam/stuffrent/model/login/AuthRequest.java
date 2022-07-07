package com.unnamedteam.stuffrent.model.login;

import lombok.Data;

import javax.validation.constraints.NotBlank;

import static com.unnamedteam.stuffrent.constants.ExceptionMessages.blankPasswordExceptionMessage;
import static com.unnamedteam.stuffrent.constants.ExceptionMessages.blankUsernameExceptionMessage;

@Data
public class AuthRequest {
    @NotBlank(message = blankUsernameExceptionMessage)
    private String username;
    @NotBlank(message = blankPasswordExceptionMessage)
    private String password;
}
