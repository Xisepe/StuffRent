package com.unnamedteam.stuffrent.model.client;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class Stuff implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Long userId;
    private String name;
    @Lob
    private byte[] photo;
    private int cost;
    @Enumerated(EnumType.STRING)
    private Tags tag;
    private boolean isRented;
}
