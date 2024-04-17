package com.app.employsoft;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Assertions;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import com.app.employsoft.auth.controllers.AuthenticationController;
import com.app.employsoft.auth.dto.AuthCreateUserRequest;
import com.app.employsoft.auth.dto.AuthLoginRequest;
import com.app.employsoft.auth.entities.UserEntity;
import com.app.employsoft.auth.exceptions.InvalidCredentialsException;
import com.app.employsoft.auth.exceptions.UserAlreadyExistsException;
import com.app.employsoft.auth.services.UserDetailServiceImpl;

@SpringBootTest
public class AuthenticationControllerTests {

    @MockBean
    private UserDetailServiceImpl userDetailServiceMock;

    @Autowired
    private AuthenticationController authenticationController;

    // Test Successful Sign Up
    @Test
    void testSignUp_Success() throws Exception {
        // Mock user creation
        AuthCreateUserRequest createUserRequest = new AuthCreateUserRequest("johndoe", "password",
                "john.doe@example.com", "John", "Doe");
        // Perform sign up request
        ResponseEntity<?> response = authenticationController.signUp(createUserRequest);

        // Assert successful response
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Map<String, String> bodyMap = (Map<String, String>) response.getBody();
        assertEquals("User created successfully", bodyMap.get("message"));

    }

    // Test Sign Up with Existing Username
    @Test
    void testSignUp_ExistingUsername() throws Exception {
        AuthCreateUserRequest createUserRequest = new AuthCreateUserRequest("johndoe", "password",
                "john.doe@example.com", "John", "Doe");
        Mockito.when(userDetailServiceMock.createUser(createUserRequest)).thenThrow(new UserAlreadyExistsException());

        // Perform sign up request (exception expected)
        authenticationController.signUp(createUserRequest);

        // Assert exception thrown
        Assertions.assertThrows(UserAlreadyExistsException.class, () -> authenticationController.signUp(createUserRequest));
    }

    // Test Login with Valid Credentials
    // @Test
    // public void testLogin_Success() throws Exception {
    // String username = "johndoe";
    // String password = "password";
    // UserEntity userDetailsMock = new UserEntity(username,
    // passwordEncoder.encode(password), true, true, true, true,
    // Collections.emptyList());
    // Authentication mockAuthentication = new
    // UsernamePasswordAuthenticationToken(username, password,
    // userDetailsMock.getAuthorities());
    // Mockito.when(userDetailServiceMock.loadUserByUsername(username)).thenReturn(userDetailsMock);
    // Mockito.when(userDetailServiceMock.authenticate(username,
    // password)).thenReturn(mockAuthentication);

    // AuthLoginRequest loginRequest = new AuthLoginRequest(username, password);

    // // Perform login request
    // ResponseEntity<?> response = authenticationController.login(loginRequest);

    // // Assert successful response
    // assertEquals(HttpStatus.OK, response.getStatusCode());
    // assertTrue(((Map<String, String>) response.getBody()).get("success"));
    // }

    // Test Login with Invalid Credentials
    @Test
    void testLogin_InvalidCredentials() throws Exception {
        String username = "johndoe";
        String password = "wrongpassword";
        Mockito.when(userDetailServiceMock.loadUserByUsername(username)).thenReturn(null);

        AuthLoginRequest loginRequest = new AuthLoginRequest(username, password);

        // Perform login request (exception expected)
        authenticationController.login(loginRequest);

        // Perform login request (exception expected)
        Assertions.assertThrows(InvalidCredentialsException.class, () -> authenticationController.login(loginRequest));
    }
}
