package com.unnamedteam.stuffrent.model.client.mapper;

import com.unnamedteam.stuffrent.model.client.UserDataDTO;
import com.unnamedteam.stuffrent.model.client.Users;

public class UserDataDTOMapper {
    public static UserDataDTO getDTO(Users user) {
        if (user.getUserData() == null) {
            return null;
        }
        return new UserDataDTO(
                user.getUserData().getFirstName(),
                user.getUserData().getLastName(),
                user.getUserData().getThirdName(),
                user.getUserData().getPhoneNumber(),
                user.getUserData().getEmailAddress(),
                user.getUserData().getAddress());
    }
}
