package com.unnamedteam.stuffrent.service;

import com.unnamedteam.stuffrent.model.client.UserData;

public interface UserDataService {
    UserData getUserDataByOwnerId(Long id);
}
