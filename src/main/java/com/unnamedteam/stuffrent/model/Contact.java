package com.unnamedteam.stuffrent.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Embeddable
public class Contact implements Serializable {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
}
