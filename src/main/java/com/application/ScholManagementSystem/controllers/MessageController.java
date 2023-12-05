package com.application.ScholManagementSystem.controllers;



import com.application.ScholManagementSystem.entities.Message;
import com.application.ScholManagementSystem.services.message.MessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {

    private final MessageServiceImpl messageServiceImpl;

    @Autowired
    public MessageController(MessageServiceImpl messageServiceImpl) {
        this.messageServiceImpl = messageServiceImpl;
    }

    @GetMapping
    public List<Message> getMessages() {
        return messageServiceImpl.getAllMessages();
    }

    @PostMapping
    public Message sendMessage(@RequestBody Message message) {
        return messageServiceImpl.sendMessage(message);
    }
}
