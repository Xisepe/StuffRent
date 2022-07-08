package com.unnamedteam.stuffrent.model.client;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Getter@Setter
@NoArgsConstructor
public class Stuff implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JsonIgnore
    private Long userId;
    private String name;
    @Lob
    private byte[] photo;
    private int cost;
    @Enumerated(EnumType.STRING)
    private Tags tag;
    private boolean isRented;
}
