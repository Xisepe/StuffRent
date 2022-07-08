package com.unnamedteam.stuffrent.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unnamedteam.stuffrent.model.client.DTO.StuffDTO;
import com.unnamedteam.stuffrent.model.client.Stuff;
import com.unnamedteam.stuffrent.repository.StuffEntityRepository;
import com.unnamedteam.stuffrent.service.StuffService;
import jdk.nashorn.internal.parser.JSONParser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class StuffServiceImpl implements StuffService {

    private StuffEntityRepository stuffEntityRepository;

    @Override
    public void saveStuff(Stuff stuff, byte[] bytes, String stuffDTO) {
        try{
            ObjectMapper om = new ObjectMapper();
            StuffDTO dto;
            dto = om.readValue(stuffDTO, StuffDTO.class);
            stuff.setName(dto.getName());
            stuff.setCost(dto.getCost());
            stuff.setTag(dto.getTag());
            stuff.setPhoto(bytes);
            stuff.setRented(false);
            stuffEntityRepository.save(stuff);
        }catch (IOException e) {
            throw new RuntimeException();
        }

    }

    @Override
    public List<Stuff> findAllUserStuffByUserId(Long userId) {
        return stuffEntityRepository.findAllStuffByUserId(userId);
    }
}
