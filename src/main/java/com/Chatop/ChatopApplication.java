package com.Chatop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

// Indicates that this is a Spring Boot application
@SpringBootApplication
// Enables Swagger2 documentation
@EnableSwagger2
public class ChatopApplication {

  // The main method that starts the Spring Boot application
  public static void main(String[] args) {
    // Runs the Spring Boot application
    SpringApplication.run(ChatopApplication.class, args);
  }

}
