package com.unnamedteam.stuffrent.model.client.DTO;

import com.unnamedteam.stuffrent.model.client.advert.Category;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.net.URL;

@Data
@AllArgsConstructor
public class ResponseAdvert implements Serializable {
    private String name;
    private Category category;
    private Integer price;
    private Boolean isRented;
    private Long rentedById;
    private URL url;

}
