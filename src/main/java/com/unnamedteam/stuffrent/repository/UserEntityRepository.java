package com.unnamedteam.stuffrent.repository;

import com.unnamedteam.stuffrent.model.client.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEntityRepository extends JpaRepository<Users, Long> {
    Users findUserByUsername(String username);
    Users findUserById(Long id);
}
