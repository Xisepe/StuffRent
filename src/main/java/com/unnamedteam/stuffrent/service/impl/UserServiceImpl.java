package com.unnamedteam.stuffrent.service.impl;

import com.unnamedteam.stuffrent.exeptions.NumberOfAdvertsExceededException;
import com.unnamedteam.stuffrent.exeptions.UserNotFoundException;
import com.unnamedteam.stuffrent.model.client.user.CashAccount;
import com.unnamedteam.stuffrent.model.client.user.Role;
import com.unnamedteam.stuffrent.repository.CashAccountEntityRepository;
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
    private final CashAccountEntityRepository cashRepository;

    @Override
    public Users saveUser(Users users) {
        Role userRole = roleRepository.findByName("ROLE_USER");
        users.setRole(userRole);
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        users.setNumberOfAdverts(0);
        Users toReturn = userRepository.save(users);
        CashAccount account = new CashAccount();
        account.setUserId(toReturn.getId());
        account.setAmount(0);
        cashRepository.save(account);
        return toReturn;
    }

    @Override
    public Users findUserById(Long id) {
        Users userById = userRepository.findUserById(id);
        checkUser(userById);
        return userById;
    }

    @Override
    public void checkUser(Users user) {
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }
    }

    @Override
    public void checkNumberOfAdverts(Users user) {
        if (user.getNumberOfAdverts() >= 10) {
            throw new NumberOfAdvertsExceededException("Ограничение 10 объявлений");
        }
    }

    @Override
    public Users findUserByUsernameWithCheck(String username) {
        Users userByUsername = userRepository.findUserByUsername(username);
        checkUser(userByUsername);
        return userByUsername;
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
