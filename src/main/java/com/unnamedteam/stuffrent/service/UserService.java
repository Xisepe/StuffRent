package com.unnamedteam.stuffrent.service;

import com.unnamedteam.stuffrent.model.client.user.Users;

public interface UserService {
    Users findUserByUsername(String username);
    Users findUserByUsernameAndPassword(String username, String password);
    Users saveUser(Users users);
    Users findUserById(Long id);
    void checkUser(Users user);
    void checkNumberOfAdverts(Users user);
}
