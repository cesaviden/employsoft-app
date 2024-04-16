package com.app.employsoft.api.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.app.employsoft.api.dto.MessageDTO;
import com.app.employsoft.api.services.implementations.MessageServiceImpl;

@RestController
@RequestMapping("/api/v1/messages")
public class MessageController {


    private MessageServiceImpl messageService;

    public MessageController(MessageServiceImpl messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public ResponseEntity<?> getAllMessages() {
        return messageService.getAllMessages();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMessageById(@PathVariable Long id) {
        return messageService.getMessageById(id);
    }

    @PostMapping
    public ResponseEntity<?> saveMessage(@RequestBody MessageDTO message) {
        return messageService.saveMessage(message);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMessage(@PathVariable Long id, @RequestBody MessageDTO message) {
        return messageService.updateMessage(id, message);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMessage(@PathVariable Long id) {
        return messageService.deleteMessage(id);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAllMessages() {
        return messageService.deleteAllMessages();
    }
}
