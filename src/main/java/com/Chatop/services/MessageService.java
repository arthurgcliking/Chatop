package com.Chatop.services;

import com.Chatop.model.DAO.MessageDAO;
import com.Chatop.repository.MessageRepository;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public MessageDAO createMessage(MessageDAO message) {
        return messageRepository.save(message);
    }
}
