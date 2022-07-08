package com.unnamedteam.stuffrent.model.client.DTO;

import com.unnamedteam.stuffrent.model.client.Tags;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import static com.unnamedteam.stuffrent.constants.ExceptionMessages.addressPatternException;

@Data
public class StuffDTO {
    @NotBlank
    @Pattern(regexp = "^[ЁёА-я 0-9]+$", message = addressPatternException)
    private String name;
    @NotNull
    private int cost;
    @NotBlank
    private Tags tag;
}
