package com.Chatop.model;

import java.io.Serializable;

public class JWTResponse implements Serializable {
    private static final long serialVersionUID = 4L;
    private final String jwttoken;

    public JWTResponse(String jwttoken) {
        this.jwttoken = jwttoken;
    }

    public String getToken() {
        return this.jwttoken;
    }
}
