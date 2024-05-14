package com.Chatop.controllers;

import io.swagger.annotations.ApiOperation;
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

// This class is used to handle user-related requests
@RestController
@RequestMapping("/api/user")
public class UserController {

    // This field is used to inject the UserService object
    @Autowired
    private UserService userService;

    // This constructor is used to initialize the UserService object
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // This method is used to get a specific user by ID
    // It returns a UserDTO object
    @GetMapping("/{id}")
    @ApiOperation(value = "Get a specific user")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Integer id) {
        Optional<UserDAO> user = userService.findById(id);
        if (user.isPresent()) {
            UserDTO userDto = convertToDto(user.get());
            return ResponseEntity.ok(userDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // This method is used to convert a UserDAO object to a UserDTO object
    private UserDTO convertToDto(UserDAO userDao) {
        UserDTO userDTO = new UserDTO(
                userDao.getId(),
                userDao.getName(),
                userDao.getEmail(),
                userDao.getCreatedAt(),
                userDao.getUpdatedAt());
        return userDTO;
    }
}
