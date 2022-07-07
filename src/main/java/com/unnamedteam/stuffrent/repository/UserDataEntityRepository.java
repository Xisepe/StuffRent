package com.unnamedteam.stuffrent.repository;

import com.unnamedteam.stuffrent.model.client.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDataEntityRepository extends JpaRepository<UserData, Long> {
}
