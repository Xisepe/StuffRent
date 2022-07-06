package com.unnamedteam.stuffrent.service;

import com.unnamedteam.stuffrent.model.Users;

import java.util.List;

public interface UserService {
    Users findUserByUsername(String username);
    Users findUserByUsernameAndPassword(String username, String password);
    List<Users> findAllUsers();
    Users saveUser(Users users);
}
