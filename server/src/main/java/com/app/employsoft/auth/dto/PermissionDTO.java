package com.app.employsoft.auth.dto;

import lombok.Builder;

@Builder
public record PermissionDTO(
    Long id,
    String name
) {
    
}
