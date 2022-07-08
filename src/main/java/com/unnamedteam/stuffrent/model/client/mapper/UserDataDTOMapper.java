package com.unnamedteam.stuffrent.model.client.mapper;

import com.unnamedteam.stuffrent.model.client.DTO.UserDataDTO;
import com.unnamedteam.stuffrent.model.client.UserData;

public class UserDataDTOMapper {
    public static UserDataDTO getDTO(UserData userData) {
        if (userData == null) {
            return null;
        }
        return new UserDataDTO(
                userData.getFirstName(),
                userData.getLastName(),
                userData.getThirdName(),
                userData.getPhoneNumber(),
                userData.getEmailAddress(),
                userData.getAddress());
    }
}
