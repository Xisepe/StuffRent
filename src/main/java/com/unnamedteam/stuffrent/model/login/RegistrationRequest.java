package com.unnamedteam.stuffrent.model.login;

import lombok.Data;
import javax.validation.constraints.*;

@Data
public class RegistrationRequest {
    @NotBlank(message = "Username cannot be blank!")
    private String username;
    @NotBlank(message = "Password cannot be blank!")
    private String password;

}
