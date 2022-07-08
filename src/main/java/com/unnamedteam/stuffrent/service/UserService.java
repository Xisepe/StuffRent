package com.unnamedteam.stuffrent.service;

import com.unnamedteam.stuffrent.model.client.DTO.UserDataDTO;
import com.unnamedteam.stuffrent.model.client.UserData;
import com.unnamedteam.stuffrent.model.client.Users;

public interface UserService {
    Users findUserByUsername(String username);
    Users findUserByUsernameAndPassword(String username, String password);
    Users saveUser(Users users);
}
