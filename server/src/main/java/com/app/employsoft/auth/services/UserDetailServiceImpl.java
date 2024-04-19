package com.app.employsoft.auth.services;

import com.app.employsoft.auth.dto.AuthCreateUserRequest;
import com.app.employsoft.auth.dto.AuthLoginRequest;
import com.app.employsoft.auth.dto.AuthResponse;
import com.app.employsoft.auth.entities.RoleEntity;
import com.app.employsoft.auth.entities.UserEntity;
import com.app.employsoft.auth.exceptions.InvalidCredentialsException;
import com.app.employsoft.auth.repositories.RoleDAO;
import com.app.employsoft.auth.repositories.UserDAO;
import com.app.employsoft.auth.utils.JwtUtils;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private JwtUtils jwtUtils;

    private PasswordEncoder passwordEncoder;

    private UserDAO userDAO;

    private RoleDAO roleDAO;

    public UserDetailServiceImpl(JwtUtils jwtUtils, PasswordEncoder passwordEncoder, UserDAO userDAO,
            RoleDAO roleDAO) {
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
        this.userDAO = userDAO;
        this.roleDAO = roleDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {

        UserEntity userEntity = userDAO.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid username or password"));

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        userEntity.getRoles().forEach(
                role -> authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRoleEnum().name()))));

        userEntity.getRoles().stream().flatMap(role -> role.getPermissionList().stream())
                .forEach(permission -> authorityList.add(new SimpleGrantedAuthority(permission.getName())));

        return new User(userEntity.getUsername(), userEntity.getPassword(), userEntity.isEnabled(),
                userEntity.isAccountNoExpired(), userEntity.isCredentialNoExpired(), userEntity.isAccountNoLocked(),
                authorityList);
    }

    public AuthResponse createUser(AuthCreateUserRequest createRoleRequest) {

        String username = createRoleRequest.username();
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("The username cannot be empty.");
        }
        String password = createRoleRequest.password();
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("The password cannot be empty.");
        }
        String email = createRoleRequest.email();
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("The email cannot be empty.");
        }
        String name = createRoleRequest.name();
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("The name cannot be empty.");
        }
        String surname = createRoleRequest.surname();
        if (surname == null || surname.isEmpty()) {
            throw new IllegalArgumentException("The surname cannot be empty.");
        }

        UserEntity userFounded = userDAO.findByUsername(username).orElse(null);
        if (userFounded != null) {
            throw new IllegalArgumentException("The username already exists.");
        }

        List<String> rolesRequest = new ArrayList<>();
        rolesRequest.add("EMPLOYEE");

        Set<RoleEntity> roleEntityList = roleDAO.findRoleEntitiesByRoleEnumIn(rolesRequest).stream()
                .collect(Collectors.toSet());

        if (roleEntityList.isEmpty()) {
            throw new IllegalArgumentException("The roles specified does not exist.");
        }

        UserEntity userEntity = UserEntity.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .email(email)
                .name(name)
                .surname(surname)
                .roles(roleEntityList)
                .isEnabled(true)
                .accountNoLocked(true)
                .accountNoExpired(true)
                .credentialNoExpired(true)
                .build();

        @SuppressWarnings("null")
        UserEntity userSaved = userDAO.save(userEntity);

        ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();

        userSaved.getRoles().forEach(
                role -> authorities.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRoleEnum().name()))));

        userSaved.getRoles()
                .stream()
                .flatMap(role -> role.getPermissionList().stream())
                .forEach(permission -> authorities.add(new SimpleGrantedAuthority(permission.getName())));

        SecurityContextHolder.getContext();

        Authentication authentication = new UsernamePasswordAuthenticationToken(userSaved.getUsername(),
                userSaved.getPassword(), authorities);

        String accessToken = jwtUtils.createToken(authentication);

        return new AuthResponse("User created successfully", accessToken, true);
    }

    public AuthResponse loginUser(AuthLoginRequest authLoginRequest) {

        String username = authLoginRequest.username();
        String password = authLoginRequest.password();

        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("The username cannot be empty.");
        }
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("The password cannot be empty.");
        }

        Authentication authentication = this.authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = jwtUtils.createToken(authentication);
        return new AuthResponse("User logged succesfully", accessToken, true);
    }

    public Authentication authenticate(String username, String password) {
        UserDetails userDetails = this.loadUserByUsername(username);

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new InvalidCredentialsException("Incorrect password. Please try again.");
        }

        return new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());
    }

    public Boolean validateToken(String token) {
        try {
            token = token.replaceFirst("^Bearer ", "");

            jwtUtils.validateToken(token);
            return true;
        } catch (JWTVerificationException e) {
            throw new JWTVerificationException("Invalid JWT Token" + token);
        }
    }

    public String getRequestRole(String token) {
        try {

            token = token.replaceFirst("^Bearer ", "");
            DecodedJWT verifiedToken = jwtUtils.validateToken(token);

            String authorizations = jwtUtils.getSpecificClaim(verifiedToken, "authorities").asString();
            List<String> authorizationsList = new ArrayList<>(Arrays.asList(authorizations.split(",")));
            return authorizationsList.stream()
                    .filter(authorization -> authorization.startsWith("ROLE_"))
                    .map(authorization -> authorization.substring(5))
                    .findFirst()
                    .orElseThrow(() -> new JWTVerificationException("The user doesn't have any role"));

        } catch (Exception e) {
            throw new JWTVerificationException("Invalid JWT Token");
        }
    }

}