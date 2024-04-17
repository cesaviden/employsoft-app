package com.app.employsoft.api.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.employsoft.api.dto.CreateMessageRequest;
import com.app.employsoft.api.dto.MessageDTO;
import com.app.employsoft.api.services.implementations.MessageServiceImpl;

@RestController
@RequestMapping("/api/v1/messages")
@Tag(name = "Message Controller", description = "Controller for handling messages")
public class MessageController {

    private MessageServiceImpl messageService;

    public MessageController(MessageServiceImpl messageService) {
        this.messageService = messageService;
    }

    /**
     * Get all messages
     * 
     * @return messages
     */
    @GetMapping
    @Operation(summary = "Get all messages", description = "Get all messages stored in the database", responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MessageDTO.class))) })
    public ResponseEntity<?> getAllMessages() {
        return messageService.getAllMessages();
    }

    /**
     * Get message by id
     * 
     * @param id the id of the message
     * @return message
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get message by id", description = "Get a message by its id", responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = MessageDTO.class))) })
    public ResponseEntity<?> getMessageById(@PathVariable Long id) {
        return messageService.getMessageById(id);
    }

    /**
     * Save a new message
     * 
     * @param message the message to save
     * @return the saved message
     */
    @PostMapping
    @Operation(summary = "Save a new message", description = "Save a new message in the database", responses = {
            @ApiResponse(responseCode = "201", description = "Created", content = @Content(schema = @Schema(implementation = MessageDTO.class))) })
    public ResponseEntity<?> saveMessage(@RequestBody CreateMessageRequest message) {
        return messageService.saveMessage(message);
    }

    /**
     * Update a message
     * 
     * @param id      the id of the message to update
     * @param message the new message data
     * @return the updated message
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update a message", description = "Update a message in the database", responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = MessageDTO.class))) })
    public ResponseEntity<?> updateMessage(@PathVariable Long id, @RequestBody CreateMessageRequest message) {
        return messageService.updateMessage(id, message);
    }

    /**
     * Delete a message
     * 
     * @param id the id of the message to delete
     * @return nothing
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a message", description = "Delete a message from the database", responses = {
            @ApiResponse(responseCode = "204", description = "No Content") })
    public ResponseEntity<?> deleteMessage(@PathVariable Long id) {
        return messageService.deleteMessage(id);
    }

    /**
     * Delete all messages
     * 
     * @return nothing
     */
    @DeleteMapping
    @Operation(summary = "Delete all messages", description = "Delete all messages from the database", responses = {
            @ApiResponse(responseCode = "204", description = "No Content") })
    public ResponseEntity<?> deleteAllMessages() {
        return messageService.deleteAllMessages();
    }
}
