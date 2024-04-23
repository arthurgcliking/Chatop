package com.Chatop.services;

import lombok.Data;
import com.Chatop.model.Message;
import com.Chatop.repository.MessageRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public Optional<Message> getMessage(final Long id) {
        return messageRepository.findById(id);
    }

    public Iterable<Message> getMessages() {
        return messageRepository.findAll();
    }

    public void deleteMessage(final Long id) {
        messageRepository.deleteById(id);
    }

    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }
}
