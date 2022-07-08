package com.unnamedteam.stuffrent.service;

import com.unnamedteam.stuffrent.model.ThingTaken;

import java.util.List;

public interface ThingTakenService {
    ThingTaken getThingTakenByThingId(long thingId);

    List<ThingTaken> getAllThingTaken();

    ThingTaken save(ThingTaken thingTaken);

    void delete(long thingId);

}
