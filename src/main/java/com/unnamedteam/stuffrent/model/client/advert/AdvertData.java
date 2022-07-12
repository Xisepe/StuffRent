package com.unnamedteam.stuffrent.model.client.advert;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

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
    private LocalDate startRent;
    private LocalDate endRent;
}
