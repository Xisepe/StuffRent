package com.unnamedteam.stuffrent.model.client.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@Getter@Setter
@NoArgsConstructor
public class CashAccount {
    @Id
    @GeneratedValue
    private Long id;
    private Long userId;

    private int amount;
}
