package com.unnamedteam.stuffrent.model.client;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class UserDataDTO implements Serializable {
    private String firstName;
    private String lastName;
    private String thirdName;
    private String phoneNumber;
    private String emailAddress;
    private String address;
}
