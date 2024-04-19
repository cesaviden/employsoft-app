package com.app.employsoft.repositories;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.app.employsoft.api.entities.Project;
import com.app.employsoft.api.repositories.ProjectDAO;
import com.app.employsoft.auth.entities.UserEntity;

@SpringBootTest
@TestMethodOrder(org.junit.jupiter.api.MethodOrderer.OrderAnnotation.class)
public class ProjectRepositoryTests {

    @Autowired
    private ProjectDAO projectDAO;

    @Test
    @Order(1)
    void findAllBySupervisor() {
        // given
        UserEntity supervisor = new UserEntity();
        Project project1 = new Project();
        Project project2 = new Project();
        project1.setSupervisor(supervisor);
        project2.setSupervisor(supervisor);
        projectDAO.save(project1);
        projectDAO.save(project2);

        // when
        List<Project> projects = projectDAO.findAllBySupervisor(supervisor);

        // then
        assertEquals(2, projects.size());
        assertTrue(projects.containsAll(List.of(project1, project2)));
    }

    @Test
    @Order(2)
    void findAllByAssignedEmployees() {
        // given
        UserEntity employee = new UserEntity();
        Project project1 = new Project();
        Project project2 = new Project();
        project1.setAssignedEmployees(Set.of(employee));
        project2.setAssignedEmployees(Set.of(employee));
        projectDAO.save(project1);
        projectDAO.save(project2);

        // when
        List<Project> projects = projectDAO.findAllByAssignedEmployees(employee);

        // then
        assertEquals(2, projects.size());
        assertTrue(projects.containsAll(List.of(project1, project2)));
    }

    @Test
    @Order(3)
    void findAllByManager() {
        // given
        UserEntity manager = new UserEntity();
        Project project1 = new Project();
        Project project2 = new Project();
        project1.setSupervisor(manager);
        project2.setSupervisor(manager);
        projectDAO.save(project1);
        projectDAO.save(project2);
    }

    @Test
    @Order(4)
    void findAllByEmployees() {
        // given
        UserEntity employee = new UserEntity();
        Project project1 = new Project();
        Project project2 = new Project();
        project1.setAssignedEmployees(Set.of(employee));
        project2.setAssignedEmployees(Set.of(employee));
        projectDAO.save(project1);
        projectDAO.save(project2);
    }

    @Test
    @Order(5)
    void findAllByEmployeesAndManager() {
        // given
        UserEntity employee = new UserEntity();
        UserEntity manager = new UserEntity();
        Project project1 = new Project();
        Project project2 = new Project();
        project1.setAssignedEmployees(Set.of(employee));
        project2.setAssignedEmployees(Set.of(employee));
        project1.setSupervisor(manager);
        project2.setSupervisor(manager);
        projectDAO.save(project1);
        projectDAO.save(project2);
    }
}
