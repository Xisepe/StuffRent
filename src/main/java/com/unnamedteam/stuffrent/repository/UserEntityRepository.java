package com.unnamedteam.stuffrent.repository;

import com.unnamedteam.stuffrent.model.client.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserEntityRepository extends JpaRepository<Users, Long> {
    Users findUserByUsername(String username);
    List<Users> findAll();
}
