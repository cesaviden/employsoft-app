package com.app.employsoft.api.repositories;

import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.app.employsoft.api.entities.Project;
import com.app.employsoft.api.entities.Task;
import com.app.employsoft.auth.entities.UserEntity;

public interface TaskDAO extends JpaRepository<Task, Long> {

    List<Task> findAllByProjects(Set<Project> project);

    List<Task> findAllByEmployee(UserEntity employee);

    List<Task> findAllTasksControlledBySupervisor(UserEntity supervisor);

    @Modifying
    @Query("UPDATE Task t SET t.employee = :employee WHERE t.id = :taskId")
    void asignTaskToEmployee(@Param("taskId") Long taskId, @Param("employee") UserEntity employee);

}
