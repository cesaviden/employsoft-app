package com.app.employsoft.auth.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.app.employsoft.auth.dto.AuthCreateUserRequest;
import com.app.employsoft.auth.dto.AuthLoginRequest;
import com.app.employsoft.auth.exceptions.UserAlreadyExistsException;
import com.app.employsoft.auth.exceptions.InvalidCredentialsException;
import com.app.employsoft.auth.services.UserDetailServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * This controller handles the Sign Up and Log In operations.
 */
@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = { "http://localhost:4200" })
@Tag(name = "Authentication Controller", description = "Controller for handling the authentication operations.")
public class AuthenticationController {

    private UserDetailServiceImpl userDetailService;

    public AuthenticationController(UserDetailServiceImpl userDetailService) {
        this.userDetailService = userDetailService;
    }

    /**
     * This endpoint is used to create a new user in the application.
     * 
     * @param authCreateUser contains the data for the new user
     * @return the created user and a 201 Created status code
     */
    @PostMapping("/sign-up")
    @Operation(summary = "Create a new user", description = "Create a new user in the application")

    @ApiResponse(responseCode = "201", description = "The user was created successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthCreateUserRequest.class)))
    @ApiResponse(responseCode = "400", description = "The user already exists", content = @Content)
    public ResponseEntity<?> signUp(@RequestBody @Valid AuthCreateUserRequest authCreateUser) {
        try {
            return new ResponseEntity<>(this.userDetailService.createUser(authCreateUser), HttpStatus.CREATED);
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.badRequest().body("The user already exists");
        }
    }

    /**
     * This endpoint is used to authenticate a user in the application.
     * 
     * @param userRequest contains the data for the user
     * @return the authenticated user and a 200 OK status code
     */
    @PostMapping("/log-in")
    @Operation(summary = "Log In", description = "Log in to the application")

    @ApiResponse(responseCode = "200", description = "The user was authenticated successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthLoginRequest.class)))
    @ApiResponse(responseCode = "400", description = "Invalid credentials", content = @Content)
    public ResponseEntity<?> login(@RequestBody @Valid AuthLoginRequest userRequest) {
        try {
            return new ResponseEntity<>(this.userDetailService.loginUser(userRequest), HttpStatus.OK);
        } catch (InvalidCredentialsException e) {
            return ResponseEntity.badRequest().body("Invalid credentials. Please try again.");
        }
    }

    /**
     * This endpoint validates the token sent in the Authorization header.
     *
     * @param request The HTTP request object
     * @return ResponseEntity containing the validation status (isValid: true/false)
     */
    @PostMapping("/validate-token")
    @Operation(summary = "Validate Token", description = "Validate the JWT token sent in the Authorization header")
    @ApiResponse(responseCode = "200", description = "Token is valid", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Map.class)))
    @ApiResponse(responseCode = "401", description = "Invalid token", content = @Content)
    public ResponseEntity<?> validateToken(HttpServletRequest request) {
        return new ResponseEntity<>(userDetailService.validateToken(request.getHeader("Authorization")), HttpStatus.OK);
    }

    @GetMapping("/request-role")
    @Operation(summary = "Request Role", description = "Request the role of the user")
    @ApiResponse(responseCode = "200", description = "The role was requested successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Map.class)))
    @ApiResponse(responseCode = "401", description = "Invalid token", content = @Content)
    public ResponseEntity<?> getRequestRole(HttpServletRequest request) {
        return new ResponseEntity<>(userDetailService.getRequestRole(request.getHeader("Authorization")), HttpStatus.OK);
    }
}
