package com.app.employsoft.auth.services;

import com.app.employsoft.auth.dto.CreateUserRequest;
import com.app.employsoft.auth.dto.UserResponse;
import com.app.employsoft.auth.entities.UserEntity;
import com.app.employsoft.auth.mappers.implementations.UserMapperImpl;
import com.app.employsoft.auth.repositories.UserDAO;
import com.app.employsoft.auth.utils.JwtUtils;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private UserDAO userDAO;
    private UserMapperImpl userMapper;
    private JwtUtils jwtUtils;

    public UserService(UserDAO userDAO, UserMapperImpl userMapper, JwtUtils jwtUtils) {
        this.userDAO = userDAO;
        this.userMapper = userMapper;
        this.jwtUtils = jwtUtils;
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

    public UserResponse getUserByJwt(String token) {
        token = token.replaceFirst("^Bearer ", "");
        DecodedJWT decodedJWT = jwtUtils.validateToken(token);
        String username = jwtUtils.extractUsername(decodedJWT);
        System.out.println(username);
        return userMapper.toUserDto(userDAO.findByUsername(username).get());
    }
}
