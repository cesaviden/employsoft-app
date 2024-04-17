package com.app.employsoft.auth.mappers.interfaces;

import com.app.employsoft.auth.dto.UserResponse;
import com.app.employsoft.auth.entities.UserEntity;

public interface UserMapper {

    UserResponse toUserDto(UserEntity user);
}
