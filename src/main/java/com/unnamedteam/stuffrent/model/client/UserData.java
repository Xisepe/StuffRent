package com.unnamedteam.stuffrent.model.client;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class UserData implements Serializable {
    @NotBlank(message = "Name cannot be empty")

    private String firstName;
    @NotBlank(message = "Name cannot be empty")
    private String lastName;
    private String thirdName;
    private String phoneNumber;
    private String emailAddress;
    private String address;

}
