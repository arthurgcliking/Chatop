package com.Chatop.controllers;

import lombok.Data;
import java.util.List;

import com.Chatop.model.DTO.RentalDTO;

// This class is used to represent a response object that contains a list of RentalDTO objects
@Data
public class RentalResponse {
    // The rentals field is used to store the list of RentalDTO objects
    List<RentalDTO> rentals;
}
