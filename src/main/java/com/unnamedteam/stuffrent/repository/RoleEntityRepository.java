package com.unnamedteam.stuffrent.repository;

import com.unnamedteam.stuffrent.model.client.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleEntityRepository extends JpaRepository<Role, Integer> {
    Role findByName(String name);
}
