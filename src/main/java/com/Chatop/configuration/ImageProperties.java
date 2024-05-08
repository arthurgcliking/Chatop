package com.Chatop.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

// This class is used to handle the configuration properties related to images in the application
@Configuration
@Data
// The @ConfigurationProperties annotation is used to map properties from application.properties or application.yml file
// The prefix "images" indicates that the properties for this class are prefixed with "images" in the configuration file
@ConfigurationProperties(prefix = "images")
public class ImageProperties {

    // This field represents the directory where images are stored
    private String imageDir;

    // This field represents the base URL for accessing the images
    private String baseUrl;

    // This method returns the full path of the image directory as a URL, prefixed with "file:"
    public String getFullImageDir() {
        return "file:" + this.imageDir;
    }
}
