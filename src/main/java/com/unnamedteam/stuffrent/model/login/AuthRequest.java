package com.unnamedteam.stuffrent.model.login;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import static com.unnamedteam.stuffrent.constants.ExceptionMessages.*;

@Data
public class AuthRequest {
    @NotBlank(message = blankUsernameExceptionMessage)
    @Size(min = 1, max = 25)
    @Pattern(regexp = "^(?=.*[a-zA-Z0-9])(?=\\S+$).{1,25}", message = usernamePatternException)
    private String username;
    @NotBlank(message = blankPasswordExceptionMessage)
    @Size(min = 5, max = 64)
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=_])(?=\\S+$).{5,64}$", message = passwordPatternException)
    private String password;
}
