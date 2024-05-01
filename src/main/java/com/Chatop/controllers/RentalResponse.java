package com.Chatop.controllers;

import lombok.Data;
import java.util.List;

import com.Chatop.model.DTO.RentalDTO;

@Data
public class RentalResponse {
    List<RentalDTO> rentals;
}
