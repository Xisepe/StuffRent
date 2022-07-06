package com.unnamedteam.stuffrent.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Getter @Setter
@Embeddable
public class Contact {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
}
