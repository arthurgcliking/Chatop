package com.Chatop.model;

import java.io.Serializable;

public class JWTRequest implements Serializable {
    private static final long serialVersionUID = 3L;

    private String email;
    private String password;

    public JWTRequest() {}

    public JWTRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
