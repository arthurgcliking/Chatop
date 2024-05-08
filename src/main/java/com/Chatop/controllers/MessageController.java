package com.Chatop.controllers;

import io.swagger.annotations.ApiOperation;
import com.Chatop.model.DAO.MessageDAO;
import com.Chatop.model.DAO.RentalDAO;
import com.Chatop.model.DAO.UserDAO;
import com.Chatop.model.DTO.MessageDTO;
import com.Chatop.services.MessageService;
import com.Chatop.services.RentalService;
import com.Chatop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// This class is used to handle message-related requests
@RestController
@RequestMapping("/api/messages")
public class MessageController {

    // These fields are used to inject the UserService, RentalService, and MessageService objects
    @Autowired
    private UserService userService;

    @Autowired
    private RentalService rentalService;

    @Autowired
    private MessageService messageService;

    // This constructor is used to initialize the UserService, RentalService, and MessageService objects
    public MessageController(UserService userService, RentalService rentalService, MessageService messageService) {
        this.userService = userService;
        this.rentalService = rentalService;
        this.messageService = messageService;
    }

    // This method is used to save a new message
    // It takes a MessageDTO object as input, maps it to a MessageDAO object, and saves it using the MessageService
    @PostMapping
    @ApiOperation(value = "Save a new message")
    public ResponseEntity<?> createMessage(@RequestBody MessageDTO messageDTO) {
        MessageDAO message = new MessageDAO();

        UserDAO user = userService.getUserById(messageDTO.getUser_id());
        RentalDAO rental = rentalService.getRentalById(messageDTO.getRental_id());

        message.setUser(user);
        message.setRental(rental);
        message.setMessage(messageDTO.getMessage());

        messageService.createMessage(message);

        return ResponseEntity.ok("{\"message\":\"Message sent!\"}");
    }
}
