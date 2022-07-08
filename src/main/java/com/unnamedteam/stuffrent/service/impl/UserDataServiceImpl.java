package com.unnamedteam.stuffrent.service.impl;

import com.unnamedteam.stuffrent.model.client.DTO.UserDataDTO;
import com.unnamedteam.stuffrent.model.client.UserData;
import com.unnamedteam.stuffrent.model.client.mapper.UserDataFromDTOMapper;
import com.unnamedteam.stuffrent.repository.UserDataEntityRepository;
import com.unnamedteam.stuffrent.service.UserDataService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDataServiceImpl implements UserDataService {
    private UserDataEntityRepository userDataEntityRepository;

    @Override
    public void saveUserData(UserData userData, Long userId, UserDataDTO userDataDTO) {
        if (userData == null) {
            userData = new UserData();
            userData.setUserId(userId);
        }
        UserDataFromDTOMapper.updateUserDataFromDTO(userData, userDataDTO);
        userDataEntityRepository.save(userData);
    }

    @Override
    public UserData findUserDataByUserId(Long id) {
        return userDataEntityRepository.findUserDataByUserId(id);
    }
}
