package com.unnamedteam.stuffrent.service;

import com.unnamedteam.stuffrent.model.Thing;
import java.util.List;

public interface ThingService {
    Thing getThingByName(String name);

    List<Thing> getAllThings();

    Thing save(Thing thing);
}
