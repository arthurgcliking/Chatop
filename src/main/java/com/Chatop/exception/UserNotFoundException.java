package com.Chatop.exception;

// This class is used to represent a custom exception for when a user is not found
public class UserNotFoundException extends RuntimeException {

    // This constructor is used to initialize the exception with a custom message
    public UserNotFoundException(String message) {
        super(message);
    }
}
