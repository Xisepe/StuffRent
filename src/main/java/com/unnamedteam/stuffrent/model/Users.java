package com.unnamedteam.stuffrent.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_table")
@Data
public class Users implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @Embedded
    private Contact contact;
}


