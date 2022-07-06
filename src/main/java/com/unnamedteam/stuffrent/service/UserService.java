package com.unnamedteam.stuffrent.service;

import com.unnamedteam.stuffrent.model.User;

import java.util.List;

public interface UserService {
    User getUserByUsername(String username);
    List<User> getAllUsers();

}
