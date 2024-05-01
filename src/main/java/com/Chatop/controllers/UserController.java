package com.Chatop.controllers;

import com.Chatop.model.DAO.UserDAO;
import com.Chatop.model.DTO.UserDTO;
import com.Chatop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Integer id) {
        Optional<UserDAO> user = userService.findById(id);
        if (user.isPresent()) {
            UserDTO userDto = convertToDto(user.get());
            return ResponseEntity.ok(userDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private UserDTO convertToDto(UserDAO daoUser) {
        UserDTO userDTO = new UserDTO(
                daoUser.getId(),
                daoUser.getName(),
                daoUser.getEmail(),
                daoUser.getCreatedAt(),
                daoUser.getUpdatedAt());
        return userDTO;
    }
}
