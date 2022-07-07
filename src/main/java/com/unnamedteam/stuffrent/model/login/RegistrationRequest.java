package com.unnamedteam.stuffrent.model.login;

import lombok.Data;
import javax.validation.constraints.*;
import static com.unnamedteam.stuffrent.constants.ExceptionMessages.blankPasswordExceptionMessage;
import static com.unnamedteam.stuffrent.constants.ExceptionMessages.blankUsernameExceptionMessage;

@Data
public class RegistrationRequest {
    @NotBlank(message = blankUsernameExceptionMessage)
    @Size(min = 1, max = 25)
    private String username;
    @NotBlank(message = blankPasswordExceptionMessage)
    @Size(min = 7, max = 25)
    private String password;

}
