package com.Chatop.services;

import com.Chatop.exception.RessourceNotFoundException;
import com.Chatop.model.DAO.RentalDAO;
import com.Chatop.model.DAO.UserDAO;
import com.Chatop.model.DTO.RentalDTO;
import com.Chatop.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalService {
    private final RentalRepository rentalRepository;
    private final UserService userService;

    @Autowired
    public RentalService(RentalRepository rentalRepository, UserService userService) {
        this.rentalRepository = rentalRepository;
        this.userService = userService;
    }

    public List<RentalDAO> findAll() {
        return rentalRepository.findAll();
    }

    public RentalDAO getRentalById(Long id) {
        return rentalRepository.findById(id).orElseThrow(() -> new RessourceNotFoundException("Rental not found with id: " + id));
    }

    public void createRental(RentalDTO rentalDTO) {
        RentalDAO newRental = new RentalDAO();
        UserDAO owner = userService.getCurrentUser();
        newRental.setName(rentalDTO.getName());
        newRental.setSurface(rentalDTO.getSurface());
        newRental.setPrice(rentalDTO.getPrice());
        newRental.setPicturePath(rentalDTO.getPicturePath());
        newRental.setDescription(rentalDTO.getDescription());
        newRental.setOwner(owner);
        rentalRepository.save(newRental);
    }

    public RentalDTO updateRental(Long rentalId, RentalDTO rentalDetails) {
        RentalDAO rental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new RessourceNotFoundException("Rental not found with id: " + rentalId));
        rental.setName(rentalDetails.getName());
        rental.setSurface(rentalDetails.getSurface());
        rental.setPrice(rentalDetails.getPrice());
        rental.setPicturePath(rentalDetails.getPicturePath());
        rental.setDescription(rentalDetails.getDescription());
        rentalRepository.save(rental);
        return rentalDetails;
    }

    public void updateRental(RentalDAO existingRental, RentalDTO rentalDTO) {
        if (rentalDTO.getName() != null) existingRental.setName(rentalDTO.getName());
        existingRental.setSurface(rentalDTO.getSurface());
        existingRental.setPrice(rentalDTO.getPrice());
        if (rentalDTO.getPicturePath() != null) existingRental.setPicturePath(rentalDTO.getPicturePath());
        if (rentalDTO.getDescription() != null) existingRental.setDescription(rentalDTO.getDescription());
        rentalRepository.save(existingRental);
    }
}
