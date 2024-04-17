package com.app.employsoft.auth.controllers;

import com.app.employsoft.auth.dto.CreateUserRequest;
import com.app.employsoft.auth.dto.UserResponse;
import com.app.employsoft.auth.entities.UserEntity;
import com.app.employsoft.auth.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = { "http://localhost:4200" })
@Tag(name = "User Controller", description = "Controller for user management")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Retrieves a list of all users.
     *
     * @return List of all users.
     */
    @Operation(summary = "Retrieves all users", description = "Returns a list of all users")

    @ApiResponse(responseCode = "200", description = "List of all users", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class)) })
    @ApiResponse(responseCode = "500", description = "Cannot retrieve users from database", content = @Content)
    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        try {
            Set<UserResponse> users = userService.getAllUsers();
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Cannot retrieve users from database");
        }
    }

    /**
     * Retrieves a user by its id.
     *
     * @param id identifier of the user.
     * @return UserEntity with the requested user.
     */
    @Operation(summary = "Retrieves a user by its id", description = "Returns a user")
    @ApiResponse(responseCode = "200", description = "User found", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class)) })
    @ApiResponse(responseCode = "404", description = "User not found")
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        try {
            UserResponse user = userService.getUserById(id);
            if (user != null) {
                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Cannot retrieve user from database");
        }
    }

    /**
     * Updates a user by its id.
     *
     * @param id   identifier of the user to update.
     * @param user UserEntity with the updated data.
     * @return UserEntity with the updated data.
     */
    @Operation(summary = "Updates a user by its id", description = "Returns a user")

    @ApiResponse(responseCode = "200", description = "User updated", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))})
    @ApiResponse(responseCode = "404", description = "User not found")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody CreateUserRequest user) {
        try {
            UserEntity updatedUser = userService.updateUser(id, user);
            if (updatedUser != null) {
                return ResponseEntity.ok(updatedUser);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Cannot update user in database");
        }
    }
}
