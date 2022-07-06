package com.unnamedteam.stuffrent.repository.model;

import com.unnamedteam.stuffrent.model.Contact;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    @Embedded
    private Contact contact;
}
