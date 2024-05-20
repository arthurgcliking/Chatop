package com.Chatop.model.JWT;

import java.io.Serializable;

// This class is used to represent a request for a JSON Web Token (JWT)
public class JWTRequest implements Serializable {

    // This field is used to maintain compatibility with serializable interfaces
    private static final long serialVersionUID = 3L;

    // This field is used to represent the email address of the user making the request
    private String email;

    // This field is used to represent the password of the user making the request
    private String password;

    // This default constructor is used to initialize a new instance of the JWTRequest class with no parameters
    public JWTRequest() {}

    // This constructor is used to initialize a new instance of the JWTRequest class with the specified email and password
    public JWTRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // This method is used to get the email address of the user making the request
    public String getEmail() {
        return this.email;
    }

    // This method is used to set the email address of the user making the request
    public void setEmail(String email) {
        this.email = email;
    }

    // This method is used to get the password of the user making the request
    public String getPassword() {
        return this.password;
    }

    // This method is used to set the password of the user making the request
    public void setPassword(String password) {
        this.password = password;
    }

}
