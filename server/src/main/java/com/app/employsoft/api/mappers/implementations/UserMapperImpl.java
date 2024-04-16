package com.app.employsoft.api.mappers.implementations;

import org.springframework.stereotype.Service;

import com.app.employsoft.api.mappers.interfaces.UserMapper;
import com.app.employsoft.auth.dto.UserDTO;
import com.app.employsoft.auth.entities.UserEntity;


@Service
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDTO toUserDto(UserEntity user) {

        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getSurname(),
                user.getUsername(),
                user.getEmail()
        );
    }
    
}
