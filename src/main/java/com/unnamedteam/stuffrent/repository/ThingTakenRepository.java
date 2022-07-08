package com.unnamedteam.stuffrent.repository;

import com.unnamedteam.stuffrent.model.Thing;
import com.unnamedteam.stuffrent.model.ThingTaken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThingTakenRepository extends JpaRepository <ThingTaken, Long>{
    ThingTaken findThingTakenByThingId(long thingId);
    List<ThingTaken> findAll();
}
