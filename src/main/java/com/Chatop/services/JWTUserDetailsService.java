package com.Chatop.services;

import com.Chatop.model.DAO.UserDAO;
import com.Chatop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JWTUserDetailsService implements UserDetailsService {
    // Injects UserRepository into the service
    @Autowired
    private UserRepository userRepository;

    // Injects PasswordEncoder into the service
    @Autowired
    private PasswordEncoder bcryptEncoder;

    // Overrides the loadUserByUsername method from the UserDetailsService interface
    @Override
    public CustomUserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        // Calls the loadUserByUserEmail method to find the user by email
        return loadUserByUserEmail(userName);
    }

    // Finds a user by their email address
    public CustomUserDetails loadUserByUserEmail(String userEmail) throws UsernameNotFoundException {
        // Retrieves the user from the database using the UserRepository
        UserDAO user = userRepository.findByEmail(userEmail);
        // If the user is not found, throws a UsernameNotFoundException
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + userEmail);
        }
        // Returns a CustomUserDetails object with the user's details
        return new CustomUserDetails(user);
    }

    // Saves a new user to the database
    public UserDAO save(UserDAO user) {
        // Encodes the user's password using the PasswordEncoder
        user.setPassword(bcryptEncoder.encode(user.getPassword()));
        // Saves the user to the database using the UserRepository
        return userRepository.save(user);
    }
}
