package com.Chatop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// This class is used to represent a custom exception for when a resource is not found
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RessourceNotFoundException extends RuntimeException {

    // This field is used to maintain compatibility with serializable interfaces
    private static final long serialVersionUID = 6L;

    // This constructor is used to initialize the exception with a custom message
    public RessourceNotFoundException(String message) {
        super(message);
    }
}
