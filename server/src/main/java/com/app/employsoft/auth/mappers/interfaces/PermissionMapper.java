package com.app.employsoft.auth.mappers.interfaces;

import com.app.employsoft.auth.dto.PermissionDTO;
import com.app.employsoft.auth.entities.PermissionEntity;

public interface PermissionMapper {

    PermissionDTO toDto(PermissionEntity permissionEntity);
    
}
