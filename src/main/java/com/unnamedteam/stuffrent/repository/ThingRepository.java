package com.unnamedteam.stuffrent.repository;


import com.unnamedteam.stuffrent.model.Thing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThingRepository extends JpaRepository <Thing, Long>{
    Thing getThingByName(String name);

    Thing getThingById(long id);

    List<Thing> getAllThings();
    void delete(Long thingId);

    Thing save(Thing thing);
}
