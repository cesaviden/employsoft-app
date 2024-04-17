package com.app.employsoft.auth.services;

import com.app.employsoft.auth.dto.CreateUserRequest;
import com.app.employsoft.auth.dto.UserResponse;
import com.app.employsoft.auth.entities.UserEntity;
import com.app.employsoft.auth.mappers.implementations.UserMapperImpl;
import com.app.employsoft.auth.repositories.UserDAO;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private UserDAO userDAO;
    private UserMapperImpl userMapper;

    public UserService(UserDAO userDAO, UserMapperImpl userMapper) {
        this.userDAO = userDAO;
        this.userMapper = userMapper;
    }

    public Set<UserResponse> getAllUsers() {
        List<UserEntity> users =  userDAO.findAll();
        return new HashSet<>(users.stream().map(userMapper::toUserDto).toList());
    }

    public UserResponse getUserById(Long id) {
        Optional<UserEntity> userOptional = userDAO.findById(id);
        if (userOptional.isPresent()) {
            return userMapper.toUserDto(userOptional.get());
        }
        return null;
    }

    public UserEntity updateUser(Long id, CreateUserRequest user) {
        Optional<UserEntity> userOptional = userDAO.findById(id);
        if (userOptional.isPresent()) {
            UserEntity existingUser = userOptional.get();
            existingUser.setName(user.name());
            existingUser.setSurname(user.surname());
            existingUser.setUsername(user.username());
            existingUser.setEmail(user.email());
            return userDAO.save(existingUser);
        }
        return null;
    }

    public void deleteUser(Long id) {
        userDAO.deleteById(id);
    }
}
