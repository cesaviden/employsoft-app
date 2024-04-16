package com.app.employsoft.api.dto;

import java.time.LocalDate;

import com.app.employsoft.auth.dto.UserDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;


@Builder
public record MessageDTO(
    Long id,
    @NotBlank(message = "Content is required") String content,
    @NotNull(message = "Creation date is required") LocalDate creationDate,
    @NotNull(message = "Sender is required") UserDTO sender
) {
    
}
