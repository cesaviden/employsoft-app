package com.app.employsoft.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.employsoft.api.dto.CreateProjectRequest;
import com.app.employsoft.api.dto.ProjectDTO;
import com.app.employsoft.api.services.implementations.ProjectServiceImpl;

@RestController
@RequestMapping("/api/v1/projects")
public class ProjectController {

    private ProjectServiceImpl projectService;

    ProjectController(ProjectServiceImpl projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public ResponseEntity<?> getAllProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProjectById(@PathVariable Long id) {
        return projectService.getProjectById(id);
    }

    @PostMapping
    public ResponseEntity<?> saveProject(@RequestBody CreateProjectRequest project) {
        return projectService.saveProject(project);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProject(@PathVariable Long id, @RequestBody CreateProjectRequest project) {
        return projectService.updateProject(id, project);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable Long id) {
        return projectService.deleteProject(id);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAllProjects() {
        return projectService.deleteAllProjects();
    }
}
