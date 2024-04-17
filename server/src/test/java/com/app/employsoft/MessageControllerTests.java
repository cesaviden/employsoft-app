package com.app.employsoft;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.app.employsoft.api.controllers.MessageController;
import com.app.employsoft.api.dto.MessageDTO;
import com.app.employsoft.api.services.interfaces.MessageService;

@SpringBootTest
public class MessageControllerTests {

    @Autowired
    private MessageController messageController;

    @Mock
    private MessageService messageService;

    @InjectMocks
    private MessageController mockedController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    /* 
    @Test
    void testGetAllMessages_Success() throws Exception {
        // Mock message service behavior
        List<MessageDTO> messages = Arrays.asList(new MessageDTO(1L, "Test Message 1", null, null));
        Mockito.when(messageService.getAllMessages()).thenReturn((ResponseEntity<?>) ResponseEntity.ok(messages));

        // Call controller method
        ResponseEntity<?> response = messageController.getAllMessages();

        // Assert response status and body
        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<MessageDTO> responseMessages = (List<MessageDTO>) response.getBody();
        assertEquals(1, responseMessages.size());
        assertEquals("Test Message 1", responseMessages.get(0).content());
    }

    @Test
    void testGetAllMessages_EmptyList() throws Exception {
        // Mock message service behavior
        Mockito.when(messageService.getAllMessages())
                .thenReturn((ResponseEntity<?>) ResponseEntity.status(HttpStatus.NO_CONTENT).body("No messages found"));

        // Call controller method
        ResponseEntity<?> response = messageController.getAllMessages();

        // Assert response status and body
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertEquals("No messages found", response.getBody());
    }

    @Test
    void testGetMessageById_Success() throws Exception {
        // Mock message service behavior
        MessageDTO message = new MessageDTO(1L, "Test Message 1", null, null);
        Mockito.when(messageService.getMessageById(1L)).thenReturn((ResponseEntity<?>) ResponseEntity.ok(message));

        // Call controller method
        ResponseEntity<?> response = mockedController.getMessageById(1L);

        // Assert response status and body
        assertEquals(HttpStatus.OK, response.getStatusCode());
        MessageDTO responseMessage = (MessageDTO) response.getBody();
        assertEquals(1L, responseMessage.id());
        assertEquals("Test Message 1", responseMessage.content());
    }

    @Test
    void testGetMessageById_NotFound() throws Exception {
        // Mock message service behavior
        Mockito.when(messageService.getMessageById(1L)).thenReturn(
                (ResponseEntity<?>) ResponseEntity.status(HttpStatus.NOT_FOUND).body("Message 1 not retrieved"));

        // Call controller method
        ResponseEntity<?> response = mockedController.getMessageById(1L);

        // Assert response status and body
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Message 1 not retrieved", response.getBody());
    }

    @Test
    void testCreateMessage_Success() throws Exception {
        // Mock message service behavior
        MessageDTO message = new MessageDTO(1L, "Test Message 1", null, null);
        Mockito.when(messageService.createMessage(Mockito.any(MessageDTO.class)))
                .thenReturn((ResponseEntity<?>) ResponseEntity.ok(message));
    }

    @Test
    void testCreateMessage_BadRequest() throws Exception {
        // Mock message service behavior
        Mockito.when(messageService.createMessage(Mockito.any(MessageDTO.class))).thenReturn(
                (ResponseEntity<?>) ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Message not created"));
    }

    @Test
    void testDeleteMessage_Success() throws Exception {
        // Mock message service behavior
        Mockito.when(messageService.deleteMessage(1L))
                .thenReturn((ResponseEntity<?>) ResponseEntity.ok("Message 1 deleted successfully"));
    }

    @Test
    void testDeleteMessage_NotFound() throws Exception {
        // Mock message service behavior
        Mockito.when(messageService.deleteMessage(1L)).thenReturn(
                (ResponseEntity<?>) ResponseEntity.status(HttpStatus.NOT_FOUND).body("Message 1 not found"));
    }

    @Test
    void testUpdateMessage_Success() throws Exception {
        // Mock message service behavior
        MessageDTO message = new MessageDTO(1L, "Test Message 1", null, null);
        Mockito.when(messageService.updateMessage(Mockito.any(MessageDTO.class)))
                .thenReturn((ResponseEntity<?>) ResponseEntity.ok(message));
    }

    @Test
    void testUpdateMessage_NotFound() throws Exception {
        // Mock message service behavior
        Mockito.when(messageService.updateMessage(Mockito.any(MessageDTO.class))).thenReturn(
                (ResponseEntity<?>) ResponseEntity.status(HttpStatus.NOT_FOUND).body("Message 1 not found"));
    }

    @Test
    void testUpdateMessage_BadRequest() throws Exception {
        // Mock message service behavior
        Mockito.when(messageService.updateMessage(Mockito.any(MessageDTO.class))).thenReturn(
                (ResponseEntity<?>) ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Message not updated"));
    }
    */
}