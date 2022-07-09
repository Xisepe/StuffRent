package com.unnamedteam.stuffrent.model.client.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "userdata")
@Getter@Setter
@NoArgsConstructor
public class UserData implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long userId;
    private String firstName;
    private String lastName;
    private String thirdName;
    private String phoneNumber;
    private String emailAddress;
    private String address;
}
