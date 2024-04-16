package com.app.employsoft.api.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.employsoft.api.entities.Project;
import com.app.employsoft.api.entities.Task;
import com.app.employsoft.auth.entities.UserEntity;

public interface TaskDAO extends JpaRepository<Task, Long> {

    List<Task> findAllByProjects(Set<Project> projects);

    List<Task> findAllByEmployee(UserEntity employee);

    List<Task> findAllTasksControlledBySupervisor(UserEntity supervisor);
}
