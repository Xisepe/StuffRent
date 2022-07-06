package com.unnamedteam.stuffrent.repository;

import com.unnamedteam.stuffrent.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
