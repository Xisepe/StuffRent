package com.unnamedteam.stuffrent.controller.model;

import lombok.Data;
import javax.validation.constraints.*;

@Data
public class RegistrationRequest {
    @NotNull
    private String username;
    @NotNull
    private String password;

}
