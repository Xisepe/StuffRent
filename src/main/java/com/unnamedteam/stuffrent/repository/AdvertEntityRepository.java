package com.unnamedteam.stuffrent.repository;

import com.unnamedteam.stuffrent.model.client.advert.Advert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdvertEntityRepository extends JpaRepository<Advert,Long> {
    List<Advert> findAllByOwnerId(Long ownerId);
    Advert findAdvertById(Long id);
}
