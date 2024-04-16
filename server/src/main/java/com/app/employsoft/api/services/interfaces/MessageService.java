package com.app.employsoft.api.services.interfaces;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import com.app.employsoft.api.dto.MessageDTO;

public interface MessageService {

    ResponseEntity<?> getAllMessages() throws NotFoundException;

    ResponseEntity<?> getMessageById(Long messageId);

    ResponseEntity<?> saveMessage(MessageDTO message);

    ResponseEntity<?> updateMessage(Long id, MessageDTO message);

    ResponseEntity<?> deleteMessage(Long messageId);

    ResponseEntity<?> deleteAllMessages();
}
