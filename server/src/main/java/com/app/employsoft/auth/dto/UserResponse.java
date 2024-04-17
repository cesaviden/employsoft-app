package com.app.employsoft.auth.dto;

import java.util.Set;

public record UserResponse(
    Long id,
    String name,
    String surname,
    String username,
    String email,
    Set<RoleDTO> roles) {
    
}
