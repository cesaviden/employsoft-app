package com.app.employsoft.api.services.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerErrorException;
import com.app.employsoft.api.dto.MessageDTO;
import com.app.employsoft.api.entities.Message;
import com.app.employsoft.api.mappers.implementations.UserMapperImpl;
import com.app.employsoft.api.repositories.MessageDAO;
import com.app.employsoft.api.services.interfaces.MessageService;
import com.app.employsoft.auth.repositories.UserDAO;

@Service
public class MessageServiceImpl implements MessageService {

    private MessageDAO messageDAO;
    private UserMapperImpl userMapper;
    private UserDAO userDAO;

    public MessageServiceImpl(MessageDAO messageDAO, UserMapperImpl userMapper, UserDAO userDAO) {
        this.messageDAO = messageDAO;
        this.userMapper = userMapper;
        this.userDAO = userDAO;
    }

    @Override
    public ResponseEntity<?> getAllMessages() {
        try {
            List<Message> messages = messageDAO.findAll();
            if (messages.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No messages found");
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(messages.stream().map(this::messageToDTO).toList());
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

                return ResponseEntity.status(HttpStatus.OK).body(messageToDTO(message.get()));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Message " + messageId + " not retrieved");
            }
        } catch (ServerErrorException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("The message " + messageId + " could not be retrieved");
        }
    }

    @Override
    public ResponseEntity<?> saveMessage(MessageDTO message) {
        try {
            if (message.id() != null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The message id must be null");
            } else {
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(messageToDTO(messageDAO.save(messageFromDTO(message))));
            }
        } catch (ServerErrorException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Override
    public ResponseEntity<?> updateMessage(Long id, MessageDTO message) {
        try {
            Optional<Message> messageToUpdate = messageDAO.findById(id);

            if (messageToUpdate.isPresent()) {
                messageToUpdate.get().setContent(message.content());
                messageToUpdate.get().setCreationDate(message.creationDate());
                messageToUpdate.get().setSender(userDAO.findById(message.sender().id()).get());
                messageDAO.save(messageToUpdate.get());
                return ResponseEntity.status(HttpStatus.OK).body(messageToDTO(messageToUpdate.get()));
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

    private MessageDTO messageToDTO(Message message) {
        return new MessageDTO(
                message.getId(),
                message.getContent(),
                message.getCreationDate(),
                userMapper.toUserDto(message.getSender()));
    }

    private Message messageFromDTO(MessageDTO messageDTO) {
        return new Message(
                messageDTO.id(),
                messageDTO.content(),
                messageDTO.creationDate(),
                userDAO.findById(messageDTO.sender().id()).get());
    }

}
