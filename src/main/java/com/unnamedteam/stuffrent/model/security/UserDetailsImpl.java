package com.unnamedteam.stuffrent.model.security;

import com.unnamedteam.stuffrent.model.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collection;
import java.util.Collections;

public class UserDetailsImpl implements UserDetails {

    private String username;
    private String password;
    private Collection<? extends  GrantedAuthority> grantedAuthorities;

    public static UserDetailsImpl fromUserEntityToUserDetailsImpl(Users user) {
        UserDetailsImpl instance = new UserDetailsImpl();
        instance.username = user.getUsername();
        instance.password = user.getPassword();
        instance.grantedAuthorities = Collections.singletonList(new SimpleGrantedAuthority(user.getRole().getName()));
        return instance;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
