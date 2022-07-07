package com.unnamedteam.stuffrent.service;

import com.unnamedteam.stuffrent.model.client.UserDataDTO;
import com.unnamedteam.stuffrent.model.client.Users;

import java.util.List;

public interface UserService {
    Users findUserByUsername(String username);
    Users findUserByUsernameAndPassword(String username, String password);
    Users saveUser(Users users);
    void updateUserDataFromDTO(Users user, UserDataDTO userDataDTO);
}
