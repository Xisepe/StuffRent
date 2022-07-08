package com.unnamedteam.stuffrent.service.impl;

import com.unnamedteam.stuffrent.model.ThingTaken;
import com.unnamedteam.stuffrent.repository.ThingTakenRepository;
import com.unnamedteam.stuffrent.service.ThingTakenService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ThingTakenServiceImpl implements ThingTakenService{
    private final ThingTakenRepository thingTakenRepository;

    @Override
    public ThingTaken getThingTakenByThingId(long thingId){
        return thingTakenRepository.findThingTakenByThingId(thingId);
    }

    @Override
    public List<ThingTaken> getAllThingTaken() {
        return thingTakenRepository.findAll();
    }

    @Override
    public ThingTaken save(ThingTaken thing){
        return thingTakenRepository.save(thing);
    }

    @Override
    public void delete(long thingId){
        thingTakenRepository.deleteById(thingId);
    }
}
