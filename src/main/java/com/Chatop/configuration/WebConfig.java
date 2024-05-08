package com.Chatop.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// This class is used to configure the web application, including resource handling
@Configuration
public class WebConfig implements WebMvcConfigurer {

    // This field is used to inject the ImageProperties object, which contains the image directory path
    @Autowired
    private ImageProperties imageProperties;

    // This method is used to add resource handlers for serving static resources, such as images
    // It maps the "/images/**" URL path to the image directory specified in the ImageProperties object
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**")
            .addResourceLocations(imageProperties.getFullImageDir());
    }
}
