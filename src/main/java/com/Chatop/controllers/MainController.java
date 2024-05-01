package com.Chatop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.Chatop.model.DAO.UserDAO;
import com.Chatop.model.DTO.UserDTO;
import com.Chatop.repository.UserRepository;
import com.Chatop.services.UserService;

@RestController
@RequestMapping(path="/api")
public class MainController {

    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public MainController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping(path="/auth/all")
    public @ResponseBody Iterable<UserDAO> getAllUsers() {
        return userService.getUsers();
    }

    @GetMapping("/auth/me")
    public ResponseEntity<?> getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        UserDAO user = userRepository.findByName(username);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        } else {
            UserDTO userResponse = new UserDTO(
                    user.getId(),
                    user.getName(),
                    user.getEmail(),
                    user.getCreatedAt(),
                    user.getUpdatedAt()
            );
            return ResponseEntity.ok(userResponse);
        }
    }
}
