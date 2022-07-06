package com.unnamedteam.stuffrent.repository;

import com.unnamedteam.stuffrent.repository.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUsername(String username);
    List<User> findAll();
}
