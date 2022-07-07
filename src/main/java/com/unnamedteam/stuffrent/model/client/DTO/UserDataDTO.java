package com.unnamedteam.stuffrent.model.client.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import static com.unnamedteam.stuffrent.constants.ExceptionMessages.addressPatternException;
import static com.unnamedteam.stuffrent.constants.ExceptionMessages.namePatternException;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@AllArgsConstructor
public class UserDataDTO implements Serializable {
    @Size(min = 1, max = 64)
    @Pattern(regexp = "^[ЁёА-я]+$", message = namePatternException)
    private String firstName;

    @Size(min = 1, max = 64)
    @Pattern(regexp = "^[ЁёА-я]+$", message = namePatternException)
    private String lastName;

    @Size(max = 64)
    @Pattern(regexp = "^[ЁёА-я]*$", message = namePatternException)
    private String thirdName;

    @NotBlank
    @Size(min = 11, max = 12)
    @Pattern(regexp = "\\+?\\d{11}")
    private String phoneNumber;

    @Size(max = 255)
    @NotBlank
    @Email
    private String emailAddress;

    @Size(max = 255)
    @NotBlank
    @Pattern(regexp = "^[ЁёА-я 0-9]+$", message = addressPatternException)
    private String address;
}
