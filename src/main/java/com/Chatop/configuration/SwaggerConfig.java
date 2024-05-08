package com.Chatop.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

// This class is used to configure Swagger for the application
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    // This method creates a bean for the Docket object, which is used to configure Swagger
    // It selects the base package for the controllers and the paths to be documented
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.Chatop.controller"))
                .paths(PathSelectors.any())
                .build();
    }
}
