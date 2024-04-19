package com.app.employsoft.repositories;

import com.app.employsoft.api.entities.Project;
import com.app.employsoft.api.entities.Task;
import com.app.employsoft.api.repositories.ProjectDAO;
import com.app.employsoft.api.repositories.TaskDAO;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TaskRepositoryTests {

    @Autowired
    TaskDAO taskDAO;

    @Autowired
    ProjectDAO projectDAO;

    @Test
    @Order(1)
    void findAllTasks() {
        List<Task> tasks = taskDAO.findAll();
        assertEquals(4, tasks.size());
    }

    @Test
    @Order(2)
    void findById() {
        Task task = taskDAO.findById(1L).get();
        assertEquals("Task 1", task.getTitle());
    }

    @Test
    @Order(3)
    void findAllByProject() {
        HashSet<Project> projects = new HashSet<>();
        projects.add(projectDAO.findById(1L).get());
        List<Task> tasks = taskDAO.findAllByProjects(projects);

        assertEquals(2, tasks.size());
    }

    // @Test
    // @Order(4)
    // void findAllByEmployee() {
    //     List<Task> tasks = taskDAO.findAllByEmployee();
    //     assertEquals(3, tasks.size());
    // }

}
