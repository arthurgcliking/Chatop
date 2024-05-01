package com.Chatop.controllers;

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

@RestController
@RequestMapping("/api/rentals")
public class RentalController {

    private final RentalService rentalService;
    private final StorageService storageService;

    @Autowired
    public RentalController(RentalService rentalService, StorageService storageService) {
        this.rentalService = rentalService;
        this.storageService = storageService;
    }

    @GetMapping
    public ResponseEntity<RentalResponse> getAllRentals() {
        List<RentalDAO> rentals = rentalService.findAll();

        List<RentalDTO> rentalDTOs = rentals.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        RentalResponse response = new RentalResponse();
        response.setRentals(rentalDTOs);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RentalDTO> getRentalById(@PathVariable(value = "id") Long rentalId) {
        RentalDAO daoRental = rentalService.getRentalById(rentalId);
        if (daoRental == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        RentalDTO rentalDTO = convertToDto(daoRental);
        return ResponseEntity.ok(rentalDTO);
    }

    private RentalDTO convertToDto(RentalDAO daoRental) {
        RentalDTO rentalDTO = new RentalDTO();
        rentalDTO.setId(daoRental.getId());
        rentalDTO.setName(daoRental.getName());
        rentalDTO.setSurface(daoRental.getSurface());
        rentalDTO.setPrice(daoRental.getPrice());
        rentalDTO.setPicturePath(daoRental.getPicturePath());
        rentalDTO.setDescription(daoRental.getDescription());
        rentalDTO.setCreated_at(daoRental.getCreatedAt());
        rentalDTO.setUpdated_at(daoRental.getUpdatedAt());
        rentalDTO.setOwner_id(daoRental.getOwner().getId());
        return rentalDTO;
    }

    @PostMapping
    public ResponseEntity<?> createRental(@RequestPart("picture") MultipartFile picture, @ModelAttribute RentalDTO rentalDTO) throws IOException {
        String picturePath = storageService.store(picture);
        try {
            rentalDTO.setPicturePath(picturePath);
            rentalService.createRental(rentalDTO);
            return ResponseEntity.ok("{\"message\":\"Rental created!\"}");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid rental!");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRental(@PathVariable Long id, @RequestPart(value = "picture", required = false) MultipartFile picture, @ModelAttribute RentalDTO rentalDTO) throws IOException {
        RentalDAO existingRental = rentalService.getRentalById(id);
        if (existingRental == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        if (picture != null && !picture.isEmpty()) {
            String picturePath = storageService.store(picture);
            rentalDTO.setPicturePath(picturePath);
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
