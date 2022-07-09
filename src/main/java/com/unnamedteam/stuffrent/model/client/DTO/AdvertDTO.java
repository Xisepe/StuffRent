package com.unnamedteam.stuffrent.model.client.DTO;

import com.unnamedteam.stuffrent.model.client.advert.Category;
import lombok.Data;

import javax.validation.constraints.*;

import static com.unnamedteam.stuffrent.constants.ExceptionMessages.addressPatternException;

@Data
public class AdvertDTO {
    @NotBlank
    @Size(max = 32)
    @Pattern(regexp = "^[ЁёА-я 0-9]+$", message = addressPatternException)
    private String name;

    private Category category;

    @Min(0)
    private int price;
}
