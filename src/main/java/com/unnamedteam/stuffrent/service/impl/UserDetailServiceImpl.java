package com.unnamedteam.stuffrent.service.impl;

import com.unnamedteam.stuffrent.model.Users;
import com.unnamedteam.stuffrent.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = userService.findUserByUsername(username);
        if (users == null) {
            throw new UsernameNotFoundException("User: "+ username+ "not found");
        }

        return new org.springframework.security.core.userdetails.User(
                users.getUsername(),
                users.getPassword(),
                new ArrayList<>()
        );
    }
}
