package com.app.employsoft.api.dto;

import java.time.LocalDate;
import java.util.Set;

import com.app.employsoft.api.entities.enums.TaskPriority;
import com.app.employsoft.api.entities.enums.TaskStatus;
import com.app.employsoft.auth.dto.UserDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;


@Builder
public record TaskDTO(
    Long id,
    @NotBlank(message = "The title is required") String title,
    @NotBlank(message = "The description is required") String description,
    @NotNull(message = "The start date is required") LocalDate creationDate,
    @NotNull(message = "The estimated end date is required") LocalDate dueDate,
    @NotNull(message = "The status is required") TaskStatus status,
    @NotNull(message = "The priority is required") TaskPriority priority,
    @NotNull(message = "The supervisor is required") UserDTO supervisor,
    @NotNull(message = "The assigned employee is required") UserDTO employee
) {
    
}
