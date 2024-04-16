package com.app.employsoft.api.mappers.interfaces;

import com.app.employsoft.auth.dto.UserDTO;
import com.app.employsoft.auth.entities.UserEntity;

public interface UserMapper {

    UserDTO toUserDto(UserEntity user);
}
