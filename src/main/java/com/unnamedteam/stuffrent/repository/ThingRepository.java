package com.unnamedteam.stuffrent.repository;


import com.unnamedteam.stuffrent.model.Thing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ThingRepository extends JpaRepository <Thing, Long>{
    Thing findThingByName(String name);
    List<Thing> findAll();
}
