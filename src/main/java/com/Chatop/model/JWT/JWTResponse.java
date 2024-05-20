package com.Chatop.model.JWT;

import java.io.Serializable;

// This class is used to represent a response containing a JSON Web Token (JWT)
public class JWTResponse implements Serializable {

    // This field is used to maintain compatibility with serializable interfaces
    private static final long serialVersionUID = 4L;

    // This field is used to represent the JSON Web Token (JWT) contained in the response
    private final String jwttoken;

    // This constructor is used to initialize a new instance of the JWTResponse class with the specified JWT token
    public JWTResponse(String jwttoken) {
        this.jwttoken = jwttoken;
    }

    // This method is used to get the JSON Web Token (JWT) contained in the response
    public String getToken() {
        return this.jwttoken;
    }

}
