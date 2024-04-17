package com.app.employsoft.auth.mappers.implementations;

import com.app.employsoft.auth.dto.PermissionDTO;
import com.app.employsoft.auth.entities.PermissionEntity;
import com.app.employsoft.auth.mappers.interfaces.PermissionMapper;

public class PermissionMapperImpl implements PermissionMapper {

    @Override
    public PermissionDTO toDto(PermissionEntity permissionEntity) {

        return PermissionDTO.builder()
                .id(permissionEntity.getId())
                .name(permissionEntity.getName())
                .build();
    }
    
}
