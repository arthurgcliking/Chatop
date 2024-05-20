package com.Chatop.configuration;

import java.io.Serializable;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.Chatop.services.HandleUser;

// This class is used to handle JWT token creation, validation, and verification
@Component
public class JWTToken implements Serializable {

    // This is a version ID for serialization, it's a good practice to include it in any class that implements Serializable interface
    private static final long serialVersionUID = 5L;

    // This constant defines the validity of the JWT token in milliseconds
    public static final long JWT_TOKEN_VALIDITY = 5000;

    // This field is used to sign the JWT tokens, it's value is read from the application.properties or file
    @Value("${jwt.secret}")
    private String secret;

    // This method extracts the user's email from a JWT token
    public String getUserEmailFromToken(String token) {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getSubject();
    }

    // This method extracts the expiration date from a JWT token
    public Date getExpirationDateFromToken(String token) {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getExpiresAt();
    }

    // This method checks if a JWT token is expired
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    // This method generates a new JWT token for a given user
    public String generateToken(HandleUser userDetails) {
        Algorithm algorithm = Algorithm.HMAC512(secret);
        JWTCreator.Builder builder = JWT.create()
            .withSubject(userDetails.getEmail())
            .withExpiresAt(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 500));
        return builder.sign(algorithm);
    }

    // This method validates a JWT token for a given user
    public Boolean validateToken(String token, HandleUser userDetails) {
        final String userEmailFromToken = getUserEmailFromToken(token);
        return (userEmailFromToken.equals(userDetails.getEmail()) && !isTokenExpired(token));
    }

    // This method verifies a JWT token using the secret key
    public DecodedJWT verifyToken(String token) {
        Algorithm algorithm = Algorithm.HMAC512(secret);
        JWTVerifier verifier = JWT.require(algorithm).build();
        return verifier.verify(token);
    }
}
