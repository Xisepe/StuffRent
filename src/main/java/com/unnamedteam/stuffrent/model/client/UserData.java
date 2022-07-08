package com.unnamedteam.stuffrent.model.client;

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
    @Column(name = "user_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Users user;


    private String firstName;
    private String lastName;
    private String thirdName;
    private String phoneNumber;
    private String emailAddress;
    private String address;

}
