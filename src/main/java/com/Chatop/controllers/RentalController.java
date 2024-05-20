package com.Chatop.controllers;

import io.swagger.annotations.ApiOperation;
import com.Chatop.model.DAO.RentalDAO;
import com.Chatop.model.DTO.RentalDTO;
import com.Chatop.services.RentalService;
import com.Chatop.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

// This class is used to handle rental-related requests
@RestController
@RequestMapping("/api/rentals")
public class RentalController {

    // These fields are used to inject the RentalService and StorageService objects
    private final RentalService rentalService;
    private final StorageService storageService;

    // This constructor is used to initialize the RentalService and StorageService objects
    @Autowired
    public RentalController(RentalService rentalService, StorageService storageService) {
        this.rentalService = rentalService;
        this.storageService = storageService;
    }

    // This method is used to get all rentals
    // It returns a list of RentalDTO objects
    @GetMapping
    @ApiOperation(value = "Get all rentals")
    public ResponseEntity<RentalResponse> getAllRentals() {
        List<RentalDTO> rentalDTOs = rentalService.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        RentalResponse response = new RentalResponse();
        response.setRentals(rentalDTOs);
        return ResponseEntity.ok(response);
    }

    // This method is used to get a specific rental by ID
    // It returns a RentalDTO object
    @GetMapping("/{id}")
    @ApiOperation(value = "Get a specific rental")
    public ResponseEntity<RentalDTO> getRentalById(@PathVariable(value = "id") Long rentalId) {
        RentalDAO rentalDao = rentalService.getRentalById(rentalId);
        if (rentalDao == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(convertToDto(rentalDao));
    }

    // This method is used to convert a RentalDAO object to a RentalDTO object
    private RentalDTO convertToDto(RentalDAO rentalDao) {
        RentalDTO rentalDTO = new RentalDTO();
        rentalDTO.setId(rentalDao.getId());
        rentalDTO.setName(rentalDao.getName());
        rentalDTO.setSurface(rentalDao.getSurface());
        rentalDTO.setPrice(rentalDao.getPrice());
        rentalDTO.setPicturePath(rentalDao.getPicturePath());
        rentalDTO.setDescription(rentalDao.getDescription());
        rentalDTO.setCreated_at(rentalDao.getCreatedAt());
        rentalDTO.setUpdated_at(rentalDao.getUpdatedAt());
        rentalDTO.setOwner_id(rentalDao.getOwner().getId());
        return rentalDTO;
    }

    // This method is used to save a new rental
    // It takes a MultipartFile object (for the picture) and a RentalDTO object (for the rental details) as input, and saves the rental using the RentalService
    @PostMapping
    @ApiOperation(value = "Save a new rental")
    public ResponseEntity<?> createRental(@RequestPart("picture") MultipartFile picture, @ModelAttribute RentalDTO rentalDTO) throws IOException {
        try {
            rentalDTO.setPicturePath(storageService.store(picture));
            rentalService.createRental(rentalDTO);
            return ResponseEntity.ok("{\"message\":\"Rental created!\"}");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid rental!");
        }
    }

    // This method is used to update a specific rental by ID
    // It takes a MultipartFile object (for the picture) and a RentalDTO object (for the rental details) as input, and updates the rental using the RentalService
    @PutMapping("/{id}")
    @ApiOperation(value = "Update a specific rental")
    public ResponseEntity<?> updateRental(@PathVariable Long id, @RequestPart(value = "picture", required = false) MultipartFile picture, @ModelAttribute RentalDTO rentalDTO) throws IOException {
        RentalDAO existingRental = rentalService.getRentalById(id);
        if (existingRental == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        if (picture != null && !picture.isEmpty()) {
            rentalDTO.setPicturePath(storageService.store(picture));
        }
        try {
            rentalService.updateRental(existingRental, rentalDTO);
            return ResponseEntity.ok("{\"message\":\"Rental updated!\"}");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid rental!");
        }
    }
}
