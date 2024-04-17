package com.app.employsoft.api.mappers.interfaces;

import com.app.employsoft.api.dto.CreateMessageRequest;
import com.app.employsoft.api.dto.MessageDTO;
import com.app.employsoft.api.entities.Message;

public interface MessageMapper {

    Message toMessage(CreateMessageRequest messageDTO);

    MessageDTO toMessageDto(Message message);
    
}
