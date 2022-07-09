package com.unnamedteam.stuffrent.model.client.mapper;

import com.unnamedteam.stuffrent.model.client.DTO.UserDataDTO;
import com.unnamedteam.stuffrent.model.client.user.UserData;

public class UserDataFromDTOMapper {
    public static void updateUserDataFromDTO(UserData userData, UserDataDTO userDataDTO) {
        userData.setAddress(userDataDTO.getAddress());
        userData.setEmailAddress(userDataDTO.getEmailAddress());
        userData.setPhoneNumber(userDataDTO.getPhoneNumber());
        userData.setFirstName(userDataDTO.getFirstName());
        userData.setLastName(userDataDTO.getLastName());
        userData.setThirdName(userDataDTO.getThirdName());
    }
}
