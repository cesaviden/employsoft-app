package com.app.employsoft.auth.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.employsoft.auth.entities.RoleEntity;

public interface RoleDAO extends JpaRepository<RoleEntity, Long> {

    List<RoleEntity> findRoleEntitiesByRoleEnumIn(List<String> roleNames);
    
}
