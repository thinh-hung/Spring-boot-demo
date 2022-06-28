package com.example.springbootjwtdemo.security.service;

import com.example.springbootjwtdemo.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
public class UserDetailsImpl implements UserDetails {
    User user;
    public UserDetailsImpl(User user){
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(user.getRoles().getName().toString()));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }
    public User getUser(){
        return user;
    }
    @Override
    public String getUsername() {
        return user.getUsername();
    }
    public String getAvatar() {return user.getAvatar();}

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