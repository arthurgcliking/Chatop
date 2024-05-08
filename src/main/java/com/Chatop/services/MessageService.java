package com.Chatop.services;

import com.Chatop.model.DAO.MessageDAO;
import com.Chatop.repository.MessageRepository;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    // Injects MessageRepository into the service
    private final MessageRepository messageRepository;

    // Constructor to initialize the messageRepository field
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    // Creates a new message and saves it to the database using the MessageRepository
    public MessageDAO createMessage(MessageDAO message) {
        return messageRepository.save(message);
    }
}
