package com.unnamedteam.stuffrent.repository;

import com.unnamedteam.stuffrent.model.client.Stuff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StuffEntityRepository extends JpaRepository<Stuff, Long> {
    List<Stuff> getAllStuffByUserId(Long id);
}
