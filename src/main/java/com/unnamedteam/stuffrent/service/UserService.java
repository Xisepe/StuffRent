package com.unnamedteam.stuffrent.service;

import com.unnamedteam.stuffrent.repository.model.User;

import java.util.List;

public interface UserService {
    User getUserByUsername(String username);
    List<User> getAllUsers();
    User save(User user);
}
