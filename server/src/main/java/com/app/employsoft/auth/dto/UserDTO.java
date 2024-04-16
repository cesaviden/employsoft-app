package com.app.employsoft.auth.dto;

/**
 * UserDTO
 */
public record UserDTO(
    Long id,
    String name,
    String surname,
    String username,
    String email
) {
}