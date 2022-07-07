package com.unnamedteam.stuffrent.service.impl;

import com.unnamedteam.stuffrent.model.client.UserData;
import com.unnamedteam.stuffrent.repository.UserDataEntityRepository;
import com.unnamedteam.stuffrent.service.UserDataService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDataServiceImpl implements UserDataService {

    UserDataEntityRepository userDataRepository;

    @Override
    public UserData getUserDataByOwnerId(Long id) {
        return userDataRepository.getUserDataByOwnerId(id);
    }
}
