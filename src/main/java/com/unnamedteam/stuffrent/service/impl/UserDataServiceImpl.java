package com.unnamedteam.stuffrent.service.impl;

import com.unnamedteam.stuffrent.model.client.UserData;
import com.unnamedteam.stuffrent.repository.UserDataEntityRepository;
import com.unnamedteam.stuffrent.service.UserDataService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDataServiceImpl implements UserDataService {
    private UserDataEntityRepository userDataEntityRepository;
    @Override
    public void saveUserData(UserData userData) {
        userDataEntityRepository.save(userData);
    }
}
