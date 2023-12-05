package com.application.ScholManagementSystem.services.message;

import com.application.ScholManagementSystem.entities.Message;
import com.application.ScholManagementSystem.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MessageServiceImpl {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Message sendMessage(Message message) {
        message.setTimestamp(new Date());
        return messageRepository.save(message);
    }
}
