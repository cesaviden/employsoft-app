package com.app.employsoft.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.employsoft.api.entities.Project;
import com.app.employsoft.auth.entities.UserEntity;

public interface ProjectDAO extends JpaRepository<Project, Long> {

    List<Project> findAllBySupervisor(UserEntity supervisor);
    List<Project> findAllByAssignedEmployees(UserEntity employee);

}
