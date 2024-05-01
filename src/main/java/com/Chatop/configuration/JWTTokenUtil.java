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
import com.Chatop.services.CustomUserDetails;

@Component
public class JWTTokenUtil implements Serializable {
    private static final long serialVersionUID = 5L;
    public static final long JWT_TOKEN_VALIDITY = 2 * 50 * 50;
    @Value("${jwt.secret}")
    private String secret;

    public String getUserEmailFromToken(String token) {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getSubject();
    }

    public Date getExpirationDateFromToken(String token) {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getExpiresAt();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(CustomUserDetails userDetails) {
        Algorithm algorithm = Algorithm.HMAC512(secret);
        JWTCreator.Builder builder = JWT.create()
            .withSubject(userDetails.getEmail())
            .withExpiresAt(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 500));
        return builder.sign(algorithm);
    }

    public Boolean validateToken(String token, CustomUserDetails userDetails) {
        final String userEmailFromToken = getUserEmailFromToken(token);
        return (userEmailFromToken.equals(userDetails.getEmail()) && !isTokenExpired(token));
    }

    public DecodedJWT verifyToken(String token) {
        Algorithm algorithm = Algorithm.HMAC512(secret);
        JWTVerifier verifier = JWT.require(algorithm).build();
        return verifier.verify(token);
    }
}
