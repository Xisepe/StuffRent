package com.unnamedteam.stuffrent.model.client.advert;

import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Calendar;


@Embeddable
@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdvertData {
    private String name;
    @Enumerated(value = EnumType.STRING)
    private Category category;
    private Integer price;
    private String photoName;
}
