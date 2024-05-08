package com.Chatop.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Schema;
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

// This class is used to handle CRUD operations on users
@Api("API for CRUD operations on users")
@RestController
@RequestMapping(path="/api")
public class MainController {

    // These fields are used to inject the UserService and UserRepository objects
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    // This constructor is used to initialize the UserService and UserRepository objects
    public MainController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    // This method is used to get all registered users
    // It returns a list of UserDAO objects
    @ApiOperation(value = "Get all registered users")
    @GetMapping(path="/auth/all")
    @ApiResponse(
            responseCode = "200",
            description = "Success",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(ref = "#/components/schemas/UserDAO")
            )
    )
    public @ResponseBody Iterable<UserDAO> getAllUsers() {
        return userService.getUsers();
    }

    // This method is used to get the details of the currently logged-in user
    // It returns a UserDTO object containing the user's details
    @ApiOperation(value = "Get details of logged-in user")
    @ApiResponse(
            responseCode = "200",
            description = "Success",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = UserDTO.class)
            )
    )
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
