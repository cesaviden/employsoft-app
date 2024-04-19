package com.app.employsoft.repositories;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.app.employsoft.api.entities.Project;
import com.app.employsoft.api.repositories.ProjectDAO;
import com.app.employsoft.auth.entities.UserEntity;
import com.app.employsoft.auth.repositories.UserDAO;

@SpringBootTest
@TestMethodOrder(org.junit.jupiter.api.MethodOrderer.OrderAnnotation.class)
class ProjectRepositoryTests {

    @Autowired
    private ProjectDAO projectDAO;

    @Autowired
    private UserDAO userDAO;

    @Test
    @Order(1)
    void findAll() {
        List<Project> projects = projectDAO.findAll();
        assertEquals(2, projects.size());
    }

    @Test
    @Order(2)
    void findById() {
        Project project = projectDAO.findById(1L).get();
        assertEquals("Project 1", project.getName());
    }

    @Test
    @Order(3)
    void findAllBySupervisor() {
        UserEntity supervisor = userDAO.findById(1L).get();
        List<Project> projects = projectDAO.findAllBySupervisor(supervisor);
        assertEquals(2, projects.size());
    }

}
