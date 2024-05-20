package com.Chatop.services;

import java.util.Optional;
import com.Chatop.model.DAO.UserDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.Chatop.repository.UserRepository;
import lombok.Data;

@Data
@Service
public class UserService {

    // Repository for interacting with UserDAO objects in the database
    private final UserRepository userRepository;

    // Constructor for initializing the UserService with a UserRepository
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Returns all UserDAO objects in the database
    public Iterable<UserDAO> getUsers() {
        return userRepository.findAll();
    }

    // Deletes the UserDAO object with the specified ID from the database
    public void deleteUser(final Integer id) {
        userRepository.deleteById(id);
    }

    // Saves the specified UserDAO object to the database and returns the saved object
    public UserDAO saveUser(UserDAO user) {
        UserDAO savedUser = userRepository.save(user);
        return savedUser;
    }

    // Returns the UserDAO object with the specified ID from the database, or null if not found
    public UserDAO getUserById(final Integer id) {
        Optional<UserDAO> user = userRepository.findById(id);
        return user.orElse(null);
    }

    // Returns the currently authenticated UserDAO object
    public UserDAO getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String username = ((UserDetails)principal).getUsername();
            UserDAO user = userRepository.findByName(username);

            return user;
        }

        return null;
    }

    // Returns the UserDAO object with the specified ID from the database, wrapped in an Optional
    public Optional<UserDAO> findById(Integer id) {
        return  userRepository.findById(id);
    }
}
