package com.unnamedteam.stuffrent.model.client;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class UserData implements Serializable {

    @NotBlank(message = "Имя не может быть пустым")
    @Size(min = 1, max = 20)
    private String firstName;

    @NotBlank(message = "Фамилия не может быть пустой")
    @Size(min = 1, max = 20)
    private String lastName;

    @Size(max = 25)
    private String thirdName;

    @Size(min = 11, max = 12)
    @Pattern(regexp = "\\+?\\d{11}")
    private String phoneNumber;

    @Size(max = 100)
    @Pattern(regexp = "^(?=.{1,64}@)[\\p{L}0-9_-]+(\\.[\\p{L}0-9_-]+)*@"
             + "[^-][\\p{L}0-9-]+(\\.[\\p{L}0-9-]+)*(\\.[\\p{L}]{2,})$")
    private String emailAddress;

    @NotBlank
    @Size(max = 150)
    private String address;

}
