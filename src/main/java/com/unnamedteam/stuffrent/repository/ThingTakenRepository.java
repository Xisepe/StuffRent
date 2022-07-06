package com.unnamedteam.stuffrent.repository;

import com.unnamedteam.stuffrent.model.Thing;
import com.unnamedteam.stuffrent.model.ThingTaken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ThingTakenRepository extends JpaRepository<ThingTaken, Long> {
    ThingTaken findThingTakenByThing(Thing thing);
    List<ThingTaken> getAllThingTakens();
}
