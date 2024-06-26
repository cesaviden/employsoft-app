package com.app.employsoft.auth.dto;

import lombok.Builder;


@Builder
public record UserDTO(
    Long id,
    String name,
    String surname,
    String username,
    String email
) {
}