package com.unnamedteam.stuffrent.service;

import com.unnamedteam.stuffrent.model.client.DTO.StuffDTO;
import com.unnamedteam.stuffrent.model.client.Stuff;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StuffService {
    void saveStuff(Stuff stuff, byte[] bytes, String stuffDTO);

    List<Stuff> findAllUserStuffByUserId(Long userId);
}
