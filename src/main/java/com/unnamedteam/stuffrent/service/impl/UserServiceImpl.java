package com.unnamedteam.stuffrent.service.impl;

import com.unnamedteam.stuffrent.model.client.Role;
import com.unnamedteam.stuffrent.model.client.UserData;
import com.unnamedteam.stuffrent.model.client.DTO.UserDataDTO;
import com.unnamedteam.stuffrent.repository.RoleEntityRepository;
import com.unnamedteam.stuffrent.repository.UserEntityRepository;
import com.unnamedteam.stuffrent.model.client.Users;
import com.unnamedteam.stuffrent.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserEntityRepository userRepository;
    private final RoleEntityRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Users saveUser(Users users) {
        Role userRole = roleRepository.findByName("ROLE_USER");
        users.setRole(userRole);
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        return userRepository.save(users);
    }

    @Override
    public void updateUserData(Users user, UserData userData) {
        if (user.getUserData() == null) {
            user.setUserData(userData);
        } else {
            UserData tmp = user.getUserData();
            tmp.setFirstName(userData.getFirstName());
            tmp.setLastName(userData.getLastName());
            tmp.setThirdName(userData.getThirdName());
            tmp.setAddress(userData.getAddress());
            tmp.setPhoneNumber(userData.getPhoneNumber());
            tmp.setEmailAddress(userData.getEmailAddress());
            tmp.setAddress(userData.getAddress());
        }
        userRepository.save(user);
    }

    @Override
    public Users findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public Users findUserByUsernameAndPassword(String username, String password) {
        Users user = findUserByUsername(username);
        if (user != null) {
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            }
        }
        return null;
    }

}
