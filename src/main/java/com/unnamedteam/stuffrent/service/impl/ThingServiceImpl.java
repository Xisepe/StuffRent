package com.unnamedteam.stuffrent.service.impl;


import com.unnamedteam.stuffrent.model.Thing;
import com.unnamedteam.stuffrent.repository.ThingRepository;
import com.unnamedteam.stuffrent.service.ThingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ThingServiceImpl implements ThingService {
    private final ThingRepository thingRepository;

    @Override
    public Thing getThingByName(String name) {
        return thingRepository.getThingByName(name);
    }
    public Thing getThingById(long id){
        return thingRepository.getThingById(id);
    }
    @Override
    public List<Thing> getAllThings() {
        return thingRepository.findAll();
    }

    @Override
    public Thing save(Thing thing){
        return thingRepository.save(thing);
    }

    @Override
    public void delete(Long id)
    {
        thingRepository.deleteById(id);
    }
}