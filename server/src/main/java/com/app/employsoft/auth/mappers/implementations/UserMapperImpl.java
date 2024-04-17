package com.app.employsoft.auth.mappers.implementations;

import java.util.HashSet;

import org.springframework.stereotype.Service;

import com.app.employsoft.auth.dto.PermissionDTO;
import com.app.employsoft.auth.dto.RoleDTO;
import com.app.employsoft.auth.dto.UserResponse;
import com.app.employsoft.auth.entities.UserEntity;
import com.app.employsoft.auth.mappers.interfaces.UserMapper;

@Service
public class UserMapperImpl implements UserMapper {

    @Override
    public UserResponse toUserDto(UserEntity user) {

        HashSet<RoleDTO> roles = new HashSet<>();

        user.getRoles().forEach(roleEntity -> {

            HashSet<PermissionDTO> permissions = new HashSet<>();

            roleEntity.getPermissionList().forEach(permissionEntity -> {
                permissions.add(PermissionDTO.builder()
                        .id(permissionEntity.getId())
                        .name(permissionEntity.getName())
                        .build());
            });

            roles.add(RoleDTO.builder()
                    .id(roleEntity.getId())
                    .name(roleEntity.getRoleEnum().name())
                    .permissions(permissions)
                    .build());
        });

        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getSurname(),
                user.getUsername(),
                user.getEmail(),
                roles);
    }

}
