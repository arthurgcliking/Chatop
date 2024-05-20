package com.Chatop.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.Chatop.configuration.JWTEntryPoint;
import com.Chatop.services.JWTUserService;
import com.Chatop.configuration.JWTRequestFilter;

// This class is used to configure Spring Security for the application
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    // These services are used to handle JWT tokens, user details, and password encoding
    @Autowired
    private JWTEntryPoint jwtEntryPoint;
    @Autowired
    private JWTUserService jwtUserService;
    @Autowired
    private JWTRequestFilter jwtRequestFilter;
    @Autowired
    private PasswordEncoder bcryptEncoder;

    // This method configures the AuthenticationManagerBuilder to use the JWTUserService and the PasswordEncoder
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(jwtUserService).passwordEncoder(bcryptEncoder);
    }

    // This method creates a bean for the AuthenticationManager
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    // This method configures the HttpSecurity object to disable CSRF, set authorization rules, handle exceptions, and manage sessions
    // It also adds the JWTRequestFilter before the UsernamePasswordAuthenticationFilter in the filter chain
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
            .authorizeRequests().antMatchers("/api/auth/login", "/api/auth/register").permitAll()
            .antMatchers("/images/**").permitAll()
            .antMatchers("/v3/api-docs","/api/v3/**", "/v2/api-docs","/api/v2/**", "/configuration/**", "/v3/swagger-ui/**", "/v2/swagger-ui/**", "/api/swagger-ui/**", "/swagger*/**", "swagger-io/**", "/webjars/**", "/swagger-ui/**").permitAll()
            .anyRequest().authenticated().and()
            .exceptionHandling().authenticationEntryPoint(jwtEntryPoint).and().sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
