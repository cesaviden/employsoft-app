package com.app.employsoft.api.dto;

import java.time.LocalDate;
import java.util.Set;
import com.app.employsoft.api.entities.enums.ProjectStatus;
import com.app.employsoft.auth.dto.UserDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record ProjectDTO(
        Long id,
        @NotBlank(message = "The name is required") String name,
        @NotBlank(message = "The description is required") String description,
        @NotNull(message = "The start date is required") LocalDate startDate,
        @NotNull(message = "The estimated end date is required") LocalDate estimatedEndDate,
        @NotNull(message = "The status is required") ProjectStatus status,
        @NotNull(message = "The supervisor is required") UserDTO supervisor,
        @NotNull(message = "The assigned employees are required") Set<UserDTO> assignedEmployees,
        @NotNull(message = "The tasks are required") Set<TaskDTO> tasks) {
}
