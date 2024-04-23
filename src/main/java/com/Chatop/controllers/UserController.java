package com.Chatop.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @GetMapping("/")
    public ResponseEntity<?> getUser(@RequestParam Long id) {
    }
}
