package com.unnamedteam.stuffrent.service.impl;

import com.unnamedteam.stuffrent.model.client.Stuff;
import com.unnamedteam.stuffrent.repository.StuffEntityRepository;
import com.unnamedteam.stuffrent.service.StuffService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StuffServiceImpl implements StuffService {

    private StuffEntityRepository stuffEntityRepository;

    @Override
    public void saveStuff(Stuff stuff) {
        stuffEntityRepository.save(stuff);
    }

    @Override
    public List<Stuff> getAllUserStuffByUserId(Long userId) {
        return stuffEntityRepository.getAllStuffByUserId(userId);
    }
}
