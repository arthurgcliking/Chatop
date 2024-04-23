package com.Chatop.controllers;

import org.springframework.http.ResponseEntity;
import java.util.Map;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @PostMapping("/")
    public ResponseEntity<?> postMessage(@RequestBody Map<String, Object> request) {
    }
}
