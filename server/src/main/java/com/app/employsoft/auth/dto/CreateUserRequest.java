package com.app.employsoft.auth.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateUserRequest(
    @NotBlank String name,
    @NotBlank String surname,
    @NotBlank String username,
    @NotBlank String email,
    @NotBlank RoleDTO role
) {
    
}
