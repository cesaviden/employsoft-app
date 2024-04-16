package com.app.employsoft.auth.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthLoginRequest(@NotBlank(message = "The username is required") String username,
        @NotBlank(message = "The password is required") String password) {
}
