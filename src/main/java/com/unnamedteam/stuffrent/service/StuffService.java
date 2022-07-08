package com.unnamedteam.stuffrent.service;

import com.unnamedteam.stuffrent.model.client.Stuff;

import java.util.List;

public interface StuffService {
    void saveStuff(Stuff stuff);
    List<Stuff> getAllUserStuffByUserId(Long userId);
}
