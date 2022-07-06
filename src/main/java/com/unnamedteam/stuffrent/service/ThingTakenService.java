package com.unnamedteam.stuffrent.service;

import com.unnamedteam.stuffrent.model.Thing;
import com.unnamedteam.stuffrent.model.ThingTaken;

import java.util.List;

public interface ThingTakenService {
    ThingTaken getThingTakenByThing(Thing thing);

    List<ThingTaken> getAllThingTakens();

    ThingTaken save(ThingTaken thingTaken);
}
