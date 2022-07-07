package com.unnamedteam.stuffrent.model.client;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user_data")
public class UserData implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long ownerId;

    @NotBlank(message = "Имя не может быть пустым")
    private String firstName;

    @NotBlank(message = "Фамилия не может быть пустой")
    private String lastName;

    private String thirdName;

    @Size(min = 11, max = 12)
    @Pattern(regexp = "\\+?\\d{11}")
    private String phoneNumber;

    @Pattern(regexp = "^(?=.{1,64}@)[\\p{L}0-9_-]+(\\.[\\p{L}0-9_-]+)*@"
             + "[^-][\\p{L}0-9-]+(\\.[\\p{L}0-9-]+)*(\\.[\\p{L}]{2,})$")
    private String emailAddress;

    @NotBlank
    private String address;

}
