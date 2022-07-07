package com.unnamedteam.stuffrent.model.client;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@AllArgsConstructor
public class UserDataDTO implements Serializable {
    @Size(max = 255)
    @NotBlank
    private String firstName;

    @Size(max = 255)
    @NotBlank
    private String lastName;

    @Size(max = 255)
    private String thirdName;

    @Size(max = 255)
    @NotBlank
    @Size(min = 11, max = 12)
    @Pattern(regexp = "\\+?\\d{11}")
    private String phoneNumber;

    @Size(max = 255)
    @NotBlank
    @Pattern(regexp = "^(?=.{1,64}@)[\\p{L}0-9_-]+(\\.[\\p{L}0-9_-]+)*@"
                      + "[^-][\\p{L}0-9-]+(\\.[\\p{L}0-9-]+)*(\\.[\\p{L}]{2,})$")
    private String emailAddress;

    @Size(max = 255)
    @NotBlank
    private String address;
}
