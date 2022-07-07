package com.unnamedteam.stuffrent.model.client;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@AllArgsConstructor
public class UserDataDTO implements Serializable {
    @Size(max = 255)
    private String firstName;
    @Size(max = 255)
    private String lastName;
    @Size(max = 255)
    private String thirdName;
    @Size(max = 255)
    private String phoneNumber;
    @Size(max = 255)
    private String emailAddress;
    @Size(max = 255)
    private String address;
}
