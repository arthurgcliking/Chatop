package com.Chatop.configuration.JWT;

import java.io.IOException;
import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

// This class is used to handle unauthorized access to the application's resources
// It implements the AuthenticationEntryPoint interface from Spring Security
@Component
public class JWTEntryPoint implements AuthenticationEntryPoint, Serializable {

    // This is a version ID for serialization, it's a good practice to include it in any class that implements Serializable interface
    private static final long serialVersionUID = 1L;

    // This method is called when an unauthorized access attempt is detected
    // It sends an HTTP 401 Unauthorized response back to the client
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
        AuthenticationException authException) throws IOException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }
}
