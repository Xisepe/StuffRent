package com.unnamedteam.stuffrent.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "things")
public class Thing {
    @Id
    private long id;
    private long userId;
    private String name;
    private String url;
    private String description;
    private BigDecimal price;
    private Enum<Term> length;

    public Thing(){
    }
}
