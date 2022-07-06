package com.unnamedteam.stuffrent.service;

import com.unnamedteam.stuffrent.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.transaction.Transactional;
import java.util.List;

public interface UserService {
    User getUserByUsername(String username);
    List<User> getAllUsers();
    Long saveUser(User user);
}
