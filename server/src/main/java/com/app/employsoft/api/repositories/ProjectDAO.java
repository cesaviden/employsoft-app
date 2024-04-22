package com.app.employsoft.api.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


import com.app.employsoft.api.entities.Project;
import com.app.employsoft.auth.entities.UserEntity;

public interface ProjectDAO extends JpaRepository<Project, Long> {

    List<Project> findAllBySupervisor(UserEntity supervisor);
    List<Project> findAllByAssignedEmployees(List<UserEntity> employees);
    /*
    @Modifying
    @Query("UPDATE Project p SET p.assignedEmployees = array_append(p.assignedEmployees, :employee) WHERE p.id = :projectId")
    int assignEmployeeToProjectQuery(@Param("employee") UserEntity employee, @Param("projectId") Long projectId);
     */
}
