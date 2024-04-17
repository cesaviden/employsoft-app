package com.app.employsoft.api.services.interfaces;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import com.app.employsoft.api.dto.CreateMessageRequest;

public interface MessageService {

    ResponseEntity<?> getAllMessages() throws NotFoundException;

    ResponseEntity<?> getMessageById(Long messageId);

    ResponseEntity<?> saveMessage(CreateMessageRequest message);

    ResponseEntity<?> updateMessage(Long id, CreateMessageRequest message);

    ResponseEntity<?> deleteMessage(Long messageId);

    ResponseEntity<?> deleteAllMessages();
}
