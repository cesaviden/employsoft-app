package com.app.employsoft.api.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateMessageRequest(
    Long id,
    @NotBlank(message = "Content is required") String content,
    @NotNull(message = "Creation date is required") LocalDate creationDate,
    @NotNull(message = "Sender is required") Long senderId
) {
    
}
