package com.Chatop.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

// This class is used to configure the PasswordEncoder bean for Spring Security
@Configuration
public class PwdEncoder {

    // This method creates a new instance of BCryptPasswordEncoder and returns it as a PasswordEncoder bean
    // BCryptPasswordEncoder is a secure password hashing algorithm that's recommended by Spring Security
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
