package com.unnamedteam.stuffrent.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
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
