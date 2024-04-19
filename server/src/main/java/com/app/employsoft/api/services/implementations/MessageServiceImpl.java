package com.app.employsoft.api.services.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerErrorException;

import com.app.employsoft.api.dto.CreateMessageRequest;
import com.app.employsoft.api.entities.Message;
import com.app.employsoft.api.mappers.implementations.MessageMapperImpl;
import com.app.employsoft.api.repositories.MessageDAO;
import com.app.employsoft.api.services.interfaces.MessageService;
import com.app.employsoft.auth.repositories.UserDAO;

@Service
public class MessageServiceImpl implements MessageService {

    private MessageDAO messageDAO;
    private MessageMapperImpl messageMapper;
    private UserDAO userDAO;

    public MessageServiceImpl(MessageDAO messageDAO, UserDAO userDAO, MessageMapperImpl messageMapper) {
        this.messageDAO = messageDAO;
        this.userDAO = userDAO;
        this.messageMapper = messageMapper;
    }

    @Override
    public ResponseEntity<?> getAllMessages() {
        try {
            List<Message> messages = messageDAO.findAll();
            if (messages.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No messages found");
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(messages.stream().map(messageMapper::toMessageDto).toList());
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("The messages could not be retrieved");
        }
    }

    @Override
    public ResponseEntity<?> getMessageById(Long messageId) {
        try {
            Optional<Message> message = messageDAO.findById(messageId);
            if (message.isPresent()) {

                return ResponseEntity.status(HttpStatus.OK).body(messageMapper.toMessageDto(message.get()));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Message " + messageId + " not retrieved");
            }
        } catch (ServerErrorException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("The message " + messageId + " could not be retrieved");
        }
    }

    @Override
    public ResponseEntity<?> saveMessage(CreateMessageRequest message) {
        try {
            System.out.println(message);
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(messageMapper.toMessageDto(messageDAO.save(messageMapper.toMessage(message))));
        } catch (ServerErrorException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Override
    public ResponseEntity<?> updateMessage(Long id, CreateMessageRequest message) {
        try {
            Optional<Message> messageToUpdate = messageDAO.findById(id);

            if (messageToUpdate.isPresent()) {
                messageToUpdate.get().setContent(message.content());
                messageToUpdate.get().setCreationDate(message.creationDate());
                messageToUpdate.get().setSender(userDAO.findById(message.senderId()).get());
                messageDAO.save(messageToUpdate.get());
                return ResponseEntity.status(HttpStatus.OK).body(messageMapper.toMessageDto(messageToUpdate.get()));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Message with id " + id + " not found");
            }
        } catch (ServerErrorException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("The message with id " + id + " could not be updated");
        }
    }

    @Override
    public ResponseEntity<?> deleteMessage(Long messageId) {
        try {
            Optional<Message> messageToDelete = messageDAO.findById(messageId);
            if (messageToDelete.isPresent()) {
                messageDAO.delete(messageToDelete.get());
                return ResponseEntity.status(HttpStatus.OK).body("Message deleted successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Message with id " + messageId + " not found");
            }
        } catch (ServerErrorException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("The message with id " + messageId + " could not be deleted");
        }
    }

    @Override
    public ResponseEntity<?> deleteAllMessages() {
        try {
            messageDAO.deleteAll();
            return ResponseEntity.status(HttpStatus.OK).body("All messages deleted successfully");
        } catch (ServerErrorException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("The messages could not be deleted");
        }
    }
}
