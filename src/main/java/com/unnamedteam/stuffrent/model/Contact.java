package com.unnamedteam.stuffrent.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Embeddable;

@Getter @Setter
@EqualsAndHashCode
@ToString
@Embeddable
public class Contact {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
}
