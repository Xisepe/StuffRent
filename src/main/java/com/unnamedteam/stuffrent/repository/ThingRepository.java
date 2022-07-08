package com.unnamedteam.stuffrent.repository;


import com.unnamedteam.stuffrent.model.Thing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThingRepository extends JpaRepository <Thing, Long>{
    Thing findThingByName(String name);
    Thing findThingById(long id);
    List<Thing> findAll();
}
