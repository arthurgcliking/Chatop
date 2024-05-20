package com.Chatop.services;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.Chatop.model.DAO.UserDAO;

import java.util.Collections;
import java.util.Collection;

// This class is used to represent a custom implementation of the UserDetails interface, which is used by Spring Security to represent a user in the application
public class HandleUser implements UserDetails {

    // This field is used to store the UserDAO object that represents the user in the database
    private final UserDAO user;

    // This constructor is used to initialize a new HandleUser object with the specified UserDAO object
    public HandleUser(UserDAO user) {
        this.user = user;
    }

    // This method is used to get the email address of the user
    public String getEmail() {
        return user.getEmail();
    }

    // This method is used to set the email address of the user
    public void setEmail(String email) {
        user.setEmail(email);
    }

    // This method is used to get a collection of GrantedAuthority objects that represent the roles and permissions of the user
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // In this implementation, the user has no roles or permissions, so an empty list is returned
        return Collections.emptyList();
    }

    // This method is used to get the password of the user
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    // This method is used to get the username of the user
    @Override
    public String getUsername() {
        return user.getName();
    }

    // This method is used to check if the user's account has not expired
    @Override
    public boolean isAccountNonExpired() {
        // In this implementation, the user's account is always considered to be non-expired
        return true;
    }

    // This method is used to check if the user's account is not locked
    @Override
    public boolean isAccountNonLocked() {
        // In this implementation, the user's account is always considered to be non-locked
        return true;
    }

    // This method is used to check if the user's credentials (e.g. password) have not expired
    @Override
    public boolean isCredentialsNonExpired() {
        // In this implementation, the user's credentials are always considered to be non-expired
        return true;
    }

    // This method is used to check if the user is enabled (i.e. active)
    @Override
    public boolean isEnabled() {
        // In this implementation, the user is always considered to be enabled
        return true;
    }

}
