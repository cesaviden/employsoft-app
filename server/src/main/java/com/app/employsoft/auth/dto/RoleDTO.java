package com.app.employsoft.auth.dto;

import java.util.Set;

import lombok.Builder;

@Builder
public record RoleDTO(
    Long id,
    String name,
    Set<PermissionDTO> permissions
) {
    
}
