package com.app.employsoft.repositories
;

import com.app.employsoft.api.entities.Project;
import com.app.employsoft.api.entities.Task;
import com.app.employsoft.api.repositories.TaskDAO;
import com.app.employsoft.auth.entities.UserEntity;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TaskRepositoryTests {

    @Autowired
    TaskDAO taskDAO;

    @Test
    @Order(1)
    void findAllByProjects() {
        // given
        Project project = new Project();
        Task task1 = new Task();

        task1.setDescription("task1");
        task1.setCreationDate(LocalDate.now());
        task1.setDueDate(LocalDate.now().plusDays(2));

        Task task2 = new Task();
        task2.setCreationDate(LocalDate.now());
        Set<Project> projects = Set.of(project);
        task1.setProjects(projects);
        task2.setProjects(projects);
         taskDAO.save(task2);

        // when
        List<Task> tasks = taskDAO.findAllByProjects(projects);

        // then
        assertEquals(2, tasks.size());
        assertTrue(tasks.containsAll(List.of(task1, task2)));
    }

    @Test
    @Order(2)
    void findAllByEmployee() {
        // given
        UserEntity employee = new UserEntity();
        Task task1 = new Task(null, null, null, null, null, null, null, null, employee, null);
        Task task2 = new Task(null, null, null, null, null, null, null, null, employee, null);
        taskDAO.save(task1);
        taskDAO.save(task2);

        // when
        List<Task> tasks = taskDAO.findAllByEmployee(employee);

        // then
        assertEquals(2, tasks.size());
        assertTrue(tasks.containsAll(List.of(task1, task2)));
    }

    @Test
    @Order(3)
    void findAllTasksControlledBySupervisor() {
        // given
        UserEntity supervisor = new UserEntity();
        Task task1 = new Task(null, null, null, null, null, null, null, supervisor, null, null);
        Task task2 = new Task(null, null, null, null, null, null, null, supervisor, null, null);
        taskDAO.save(task1);
        taskDAO.save(task2);

        // when
        List<Task> tasks = taskDAO.findAllTasksControlledBySupervisor(supervisor);

        // then
        assertEquals(2, tasks.size());
        assertTrue(tasks.containsAll(List.of(task1, task2)));
    }
}

