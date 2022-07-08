package com.unnamedteam.stuffrent.service;

import com.unnamedteam.stuffrent.model.Thing;
import java.util.List;

public interface ThingService {
    Thing getThingByName(String name);

    Thing getThingById(long id);

    List<Thing> getAllThings();
    void delete(Long thingId);

    Thing save(Thing thing);
}