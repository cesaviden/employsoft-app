package com.app.employsoft.auth.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.app.employsoft.auth.dto.AuthCreateUserRequest;
import com.app.employsoft.auth.dto.AuthLoginRequest;
import com.app.employsoft.auth.dto.AuthResponse;
import com.app.employsoft.auth.exceptions.UserAlreadyExistsException;
import com.app.employsoft.auth.exceptions.InvalidCredentialsException;
import com.app.employsoft.auth.services.UserDetailServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = {"http://localhost:4200"})
public class AuthenticationController {

    private UserDetailServiceImpl userDetailService;

    public AuthenticationController(UserDetailServiceImpl userDetailService) {
        this.userDetailService = userDetailService;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody @Valid AuthCreateUserRequest authCreateUser) {
        try {
            return new ResponseEntity<>(this.userDetailService.createUser(authCreateUser), HttpStatus.CREATED);
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.badRequest().body("The user already exists");
        }
    }

    @PostMapping("/log-in")
    public ResponseEntity<?> login(@RequestBody @Valid AuthLoginRequest userRequest) {
        try {
            return new ResponseEntity<>(this.userDetailService.loginUser(userRequest), HttpStatus.OK);
        } catch (InvalidCredentialsException e) {
            return ResponseEntity.badRequest().body("Invalid credentials. Please try again.");
        }
    }
}