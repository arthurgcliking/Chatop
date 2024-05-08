package com.Chatop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

import com.Chatop.model.JWTRequest;
import com.Chatop.model.JWTResponse;
import com.Chatop.model.DAO.UserDAO;
import com.Chatop.services.CustomUserDetails;
import com.Chatop.services.JWTUserDetailsService;
import com.Chatop.configuration.JWTTokenUtil;

// This class is used to handle authentication and registration requests
@RestController
@CrossOrigin
public class JWTController {

    // These fields are used to inject the JWTUserDetailsService, JWTTokenUtil, and AuthenticationManager objects
    @Autowired
    private JWTUserDetailsService userDetailsService;

    @Autowired
    private JWTTokenUtil jwtTokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    // This method is used to register a new user
    // It saves the user in the database, generates a JWT token, and returns it in a JWTResponse object
    @PostMapping("/api/auth/register")
    @ApiOperation(value = "Register a new user")
    public ResponseEntity<?> saveUser(@RequestBody UserDAO user) throws Exception {
        userDetailsService.save(user);
        final CustomUserDetails userDetails = userDetailsService.loadUserByUserEmail(user.getEmail());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JWTResponse(token));
    }

    // This method is used to handle user login requests
    // It authenticates the user, generates a JWT token, and returns it in a JWTResponse object
    @PostMapping("/api/auth/login")
    @ApiOperation(value = "User login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JWTRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());
        final CustomUserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JWTResponse(token));
    }

    // This method is used to authenticate the user with the AuthenticationManager
    // It throws an exception if the user is disabled or the credentials are invalid
    private void authenticate(String login, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
