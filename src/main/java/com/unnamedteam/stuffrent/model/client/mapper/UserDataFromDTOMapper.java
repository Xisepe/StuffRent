package com.unnamedteam.stuffrent.model.client.mapper;

import com.unnamedteam.stuffrent.model.client.DTO.UserDataDTO;
import com.unnamedteam.stuffrent.model.client.UserData;

public class UserDataFromDTOMapper {
    public static UserData getUserDataFromDTO(UserDataDTO userDataDTO) {
        UserData userData = new UserData();
        userData.setAddress(userDataDTO.getAddress());
        userData.setEmailAddress(userDataDTO.getEmailAddress());
        userData.setPhoneNumber(userDataDTO.getPhoneNumber());
        userData.setFirstName(userDataDTO.getFirstName());
        userData.setLastName(userDataDTO.getLastName());
        userData.setThirdName(userDataDTO.getThirdName());
        return userData;
    }
}
