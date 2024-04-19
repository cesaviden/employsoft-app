package com.app.employsoft.api.services.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;

import com.app.employsoft.api.dto.CreateProjectRequest;
import com.app.employsoft.api.entities.Project;
import com.app.employsoft.api.mappers.implementations.ProjectMapperImpl;
import com.app.employsoft.api.repositories.ProjectDAO;
import com.app.employsoft.api.services.interfaces.ProjectService;
import com.app.employsoft.auth.entities.UserEntity;
import com.app.employsoft.auth.repositories.UserDAO;
@Service
public class ProjectServiceImpl implements ProjectService {

    private ProjectDAO projectDAO;
    private ProjectMapperImpl projectMapper;
    private UserDAO userDAO;

    public ProjectServiceImpl(ProjectDAO projectDAO, ProjectMapperImpl projectMapper, UserDAO userDAO) {
        this.projectDAO = projectDAO;
        this.projectMapper = projectMapper;
        this.userDAO = userDAO;
    }

    @Override
    public ResponseEntity<?> getAllProjects() {
        try {
            List<Project> projects = projectDAO.findAll();
            if (projects.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No projects found");
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(projects.stream().map(projectMapper::toProjectDto).toList());
            }
        } catch (InternalServerError e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("The projects could not be retrieved");
        }
    }

    public ResponseEntity<?> getProjectsBySupervisor(String username) {
        try {
            UserEntity supervisor = userDAO.findByUsername(username).get();
            List<Project> projects = projectDAO.findAllBySupervisor(supervisor);
            if (projects.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No projects found");
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(projects.stream().map(projectMapper::toProjectDto).toList());
            }
        } catch (InternalServerError e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("The projects could not be retrieved");
        }
    }

    @Override
    public ResponseEntity<?> getProjectById(Long projectId) {
        try {
            Optional<Project> project = projectDAO.findById(projectId);
            if (project.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(projectMapper.toProjectDto(project.get()));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Project " + projectId + " not retrieved");
            }
        } catch (InternalServerError e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("The project " + projectId + " could not be retrieved");
        }
    }

    @Override
    public ResponseEntity<?> saveProject(CreateProjectRequest project) {
        try {
            System.out.println(project);
            return ResponseEntity.status(HttpStatus.OK).body(projectDAO.save(projectMapper.toProject(project)));

        } catch (InternalServerError e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("The project could not be created");
        }
    }
    
    @Override
    public ResponseEntity<?> updateProject(Long projectId, CreateProjectRequest project) {

        try {

            Optional<Project> projectToUpdate = projectDAO.findById(projectId);
            if (projectToUpdate.isPresent()) {

                projectToUpdate = Optional.of(projectMapper.toProject(project));
                projectDAO.save(projectToUpdate.get());

                return ResponseEntity.status(HttpStatus.OK).body(projectMapper.toProjectDto(projectToUpdate.get()));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Project with id " + projectId + " not found");
            }
        } catch (InternalServerError e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("The project with id " + projectId + " could not be updated");
        }
    }

    @Override
    public ResponseEntity<?> deleteProject(Long projectId) {

        try {

            Optional<Project> projectToDelete = projectDAO.findById(projectId);
            if (projectToDelete.isPresent()) {
                projectDAO.delete(projectToDelete.get());
                return ResponseEntity.status(HttpStatus.OK).body("Project deleted successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Project with id " + projectId + " not found");
            }
        } catch (InternalServerError e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("The project with id " + projectId + " could not be deleted");
        }
    }

    @Override
    public ResponseEntity<?> deleteAllProjects() {
        try {
            projectDAO.deleteAll();
            if (projectDAO.count() == 0) {
                return ResponseEntity.status(HttpStatus.OK).body("All projects deleted successfully");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("The projects could not be deleted");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("The projects could not be deleted");
        }
    }
}
