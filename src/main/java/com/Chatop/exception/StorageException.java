package com.Chatop.exception;

// This class is used to represent a custom exception for when there is an error related to storage operations
public class StorageException extends RuntimeException {

    // This constructor is used to initialize the exception with a custom message
    public StorageException(String message) {
        super(message);
    }

    // This constructor is used to initialize the exception with a custom message and a cause
    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
