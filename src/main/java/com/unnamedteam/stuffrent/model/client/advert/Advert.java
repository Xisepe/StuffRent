package com.unnamedteam.stuffrent.model.client.advert;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Advert implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long ownerId;
    private Long rentedById;
    private boolean isRented;
    @Embedded
    private AdvertData advertData;
}
