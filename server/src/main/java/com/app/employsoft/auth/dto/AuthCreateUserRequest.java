package com.app.employsoft.auth.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.validation.constraints.NotBlank;

@JsonPropertyOrder({"name", "surname", "username", "email", "password"})
public record AuthCreateUserRequest(@NotBlank(message = "The username is required") String username,
        @NotBlank(message = "The email is required") String email,
        @NotBlank(message = "The name is required") String name,
        @NotBlank(message = "The surname is required") String surname,
        @NotBlank(message = "The password is required") String password) {
}
