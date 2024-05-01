package com.Chatop.services;

import java.util.Optional;
import com.Chatop.exception.UserNotFoundException;
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

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Iterable<UserDAO> getUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(final Integer id) {
        userRepository.deleteById(id);
    }

    public UserDAO saveUser(UserDAO user) {
        UserDAO savedUser = userRepository.save(user);
        return savedUser;
    }

    public UserDAO getUserById(final Integer id) {
        Optional<UserDAO> user = userRepository.findById(id);
        return user.orElse(null);
    }

    public UserDAO getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String username = ((UserDetails)principal).getUsername();
            UserDAO user = userRepository.findByName(username);

            if(user == null){
                throw new UserNotFoundException("There's no authenticated user");
            }

            return user;
        }

        throw new UserNotFoundException("There's no authenticated user");
    }

    public Optional<UserDAO> findById(Integer id) {
        return  userRepository.findById(id);
    }
}
