package com.Chatop.configuration;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.Chatop.services.HandleUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.Chatop.services.JWTUserService;

// This class is used to filter incoming HTTP requests and handle JWT authentication
@Component
public class JWTRequestFilter extends OncePerRequestFilter {

    // These services are used to handle JWT tokens and user details
    @Autowired
    private JWTUserService jwtUserService;
    @Autowired
    private JWTToken jwtTokenn;

    // This method checks if the request should be filtered or not
    // In this case, it skips filtering for login and register requests
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        return path.startsWith("/api/auth/login") || path.startsWith("/api/auth/register");
    }

    // This method is called for each request, it checks the Authorization header for a valid JWT token
    // If a valid token is found, it sets the authentication in the security context
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
        throws ServletException, IOException {
        final String requestTokenHeader = request.getHeader("Authorization");
        String username = null;
        String jwtToken = null;
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);
            try {
                DecodedJWT jwt = jwtTokenn.verifyToken(jwtToken);
                username = jwtTokenn.getUserEmailFromToken(jwtToken);
            } catch (Exception e) {
                System.out.println("Unable to get JWT Token");
            }
        } else {
            logger.warn("JWT Token dont begin with Bearer String");
        }

        // If a username is found and the security context is empty, it loads the user details and validates the token
        // If the token is valid, it sets the authentication in the security context
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            HandleUser userDetails = this.jwtUserService.loadUserByUserEmail(username);
            if (jwtTokenn.validateToken(jwtToken, userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                    .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        chain.doFilter(request, response);
    }
}
