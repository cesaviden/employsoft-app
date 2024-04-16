package com.app.employsoft.api.dto;

import java.time.LocalDate;
import java.util.Set;

import com.app.employsoft.api.entities.enums.TaskPriority;
import com.app.employsoft.api.entities.enums.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateTaskRequest(
        @NotBlank String title,
        @NotBlank String description,
        @NotNull TaskStatus status,
        @NotNull TaskPriority priority,
        @NotNull LocalDate creationDate,
        @NotNull LocalDate dueDate,
        @NotNull Set<Long> projectsId,
        @NotNull Long employeeId,
        @NotNull Long supervisorId) {

}
