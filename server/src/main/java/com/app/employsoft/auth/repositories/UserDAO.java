package com.app.employsoft.auth.repositories;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.employsoft.auth.entities.RoleEntity;
import com.app.employsoft.auth.entities.UserEntity;

public interface UserDAO extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);

    List<UserEntity> findUserEntitiesByRolesIn(HashSet<RoleEntity> roles);

}
