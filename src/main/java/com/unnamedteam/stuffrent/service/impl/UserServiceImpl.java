package com.unnamedteam.stuffrent.service.impl;

import com.unnamedteam.stuffrent.exeptions.UserNotFoundException;
import com.unnamedteam.stuffrent.model.client.user.Role;
import com.unnamedteam.stuffrent.repository.RoleEntityRepository;
import com.unnamedteam.stuffrent.repository.UserEntityRepository;
import com.unnamedteam.stuffrent.model.client.user.Users;
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
    public Users findUserById(Long id) {
        return userRepository.findUserById(id);
    }

    @Override
    public void checkUser(Users user) {
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }
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
