package com.app.employsoft.api.mappers.implementations;

import com.app.employsoft.api.dto.CreateMessageRequest;
import com.app.employsoft.api.dto.MessageDTO;
import com.app.employsoft.api.entities.Message;
import com.app.employsoft.api.mappers.interfaces.MessageMapper;
import com.app.employsoft.auth.dto.UserDTO;
import com.app.employsoft.auth.dto.UserResponse;
import com.app.employsoft.auth.repositories.UserDAO;
import org.springframework.stereotype.Service;

@Service
public class MessageMapperImpl implements MessageMapper {

    private UserDAO userDAO;

    public MessageMapperImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public Message toMessage(CreateMessageRequest createMessageRequest) {

        Message message = new Message();
        message.setContent(createMessageRequest.content());
        message.setCreationDate(createMessageRequest.creationDate());
        message.setSender(userDAO.findById(createMessageRequest.senderId()).get());
        return message;
    }

    @Override
    public MessageDTO toMessageDto(Message message) {

        return new MessageDTO(message.getId(),
                message.getContent(),
                message.getCreationDate(),
                new UserDTO(message.getSender().getId(),
                        message.getSender().getName(),
                        message.getSender().getSurname(),
                        message.getSender().getUsername(),
                        message.getSender().getEmail()));
    }

}
