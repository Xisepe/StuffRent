package com.unnamedteam.stuffrent.service;

import com.unnamedteam.stuffrent.model.client.DTO.UserDataDTO;
import com.unnamedteam.stuffrent.model.client.user.UserData;

public interface UserDataService {
    void saveUserData(UserData userData, Long userId, UserDataDTO userDataDTO);
    UserData findUserDataByUserId(Long id);
}
