package com.Chatop.services;

import com.Chatop.configuration.ImageConfig;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class StorageService {
    // The root location where images will be stored
    private final Path rootLocation;
    // The base URL where images can be accessed
    private final String baseUrl;

    // Constructor that sets the root location and base URL using the values from the ImageConfig configuration
    public StorageService(ImageConfig imageConfig) {
        this.rootLocation = Paths.get(imageConfig.getImageDir());
        this.baseUrl = imageConfig.getBaseUrl();
    }

    // Helper method to extract the root location from the base URL
    private Path extractRootLocation(String baseUrl) {
        try {
            URL url = new URL(baseUrl);
            return Paths.get(url.toURI());
        } catch (MalformedURLException | URISyntaxException e) {
            throw new IllegalArgumentException("Invalid base URL: " + baseUrl, e);
        }
    }

    // Method to store a file in the root location and return its URL
    public String store(MultipartFile file) throws IOException {
      try (InputStream is = file.getInputStream()) {
          // Get the original file name and extract its extension
          String originalFileName = file.getOriginalFilename();
          String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
          // Generate a random file name with the same extension
          String storedFileName = UUID.randomUUID().toString() + fileExtension;

          // Copy the file to the root location
          Files.copy(is, this.rootLocation.resolve(storedFileName));

          // Return the URL of the stored file
          return baseUrl + storedFileName;
      }
  }

}
