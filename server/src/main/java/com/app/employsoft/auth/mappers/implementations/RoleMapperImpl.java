package com.app.employsoft.auth.mappers.implementations;

import java.util.HashSet;
import com.app.employsoft.auth.dto.PermissionDTO;
import com.app.employsoft.auth.dto.RoleDTO;
import com.app.employsoft.auth.entities.RoleEntity;
import com.app.employsoft.auth.mappers.interfaces.RoleMapper;

public class RoleMapperImpl implements RoleMapper {

    @Override
    public RoleDTO toDto(RoleEntity roleEntity) {

        HashSet<PermissionDTO> permissions = new HashSet<>();

        roleEntity.getPermissionList().forEach(permissionEntity -> {
            permissions.add(PermissionDTO.builder()
                    .id(permissionEntity.getId())
                    .name(permissionEntity.getName())
                    .build());
        });

        return RoleDTO.builder()
                .id(roleEntity.getId())
                .name(roleEntity.getRoleEnum().name())
                .permissions(permissions)
                .build();
    }
    
}
