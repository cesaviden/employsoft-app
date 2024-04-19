package com.app.employsoft.auth.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.app.employsoft.auth.entities.UserEntity;

public interface UserDAO extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);

}
