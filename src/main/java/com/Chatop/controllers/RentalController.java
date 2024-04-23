package com.Chatop.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.Map;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {

    @GetMapping("/")
    public ResponseEntity<?> getRentals() {
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRental(@PathVariable Long id) {
    }

    @PostMapping("/")
    public ResponseEntity<?> addRental(@RequestParam Map<String, Object> request) {
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRental(@PathVariable Long id, @RequestParam Map<String, Object> request) {
    }
}
