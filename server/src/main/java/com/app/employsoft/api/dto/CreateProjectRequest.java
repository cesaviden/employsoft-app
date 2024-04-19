package com.app.employsoft.api.dto;

import java.time.LocalDate;
import java.util.Set;

import com.app.employsoft.api.entities.enums.ProjectStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * CreateProjectRequest
 */
public record CreateProjectRequest(
        Long id,
        @NotBlank String name,
        @NotBlank String description,
        @NotNull LocalDate startDate,
        @NotNull LocalDate estimatedEndDate,
        @NotNull ProjectStatus status,
        @NotNull Long supervisorId,
        @NotNull Set<Long> assignedEmployeesId,
        @NotNull Set<Long> tasksId) {
}