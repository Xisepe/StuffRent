package com.unnamedteam.stuffrent.service.impl;

import com.unnamedteam.stuffrent.repository.UserRepository;
import com.unnamedteam.stuffrent.model.User;
import com.unnamedteam.stuffrent.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Long saveUser(User user) {
        User toSave = new User();
        toSave.setUsername(user.getUsername());
        toSave.setPassword(bCryptPasswordEncoder
                .encode(user.getPassword()));
        return userRepository.save(toSave).getId();
    }
}
