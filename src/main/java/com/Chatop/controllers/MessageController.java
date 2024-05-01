package com.Chatop.controllers;

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

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final UserService userService;
    private final RentalService rentalService;
    private final MessageService messageService;

    @Autowired
    public MessageController(UserService userService, RentalService rentalService, MessageService messageService) {
        this.userService = userService;
        this.rentalService = rentalService;
        this.messageService = messageService;
    }

    @PostMapping
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
