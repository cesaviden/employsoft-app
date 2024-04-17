package com.app.employsoft.auth.mappers.interfaces;

import com.app.employsoft.auth.dto.RoleDTO;
import com.app.employsoft.auth.entities.RoleEntity;

public interface RoleMapper {

    RoleDTO toDto(RoleEntity roleEntity);
    
}
