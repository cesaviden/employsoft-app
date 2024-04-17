package com.app.employsoft.api.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.employsoft.api.dto.CreateProjectRequest;
import com.app.employsoft.api.services.implementations.ProjectServiceImpl;

/**
 * Controller for project management
 */
@RestController
@RequestMapping("/api/v1/projects")
@Tag(name = "Project Controller", description = "Controller for project management")
public class ProjectController {

    private ProjectServiceImpl projectService;

    ProjectController(ProjectServiceImpl projectService) {
        this.projectService = projectService;
    }

    /**
     * Get a list of all projects
     * 
     * @return List of projects
     */
    @GetMapping
    @Operation(summary = "Get all projects", description = "Returns a list of projects")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of projects")
    })
    public ResponseEntity<?> getAllProjects() {
        return projectService.getAllProjects();
    }

    /**
     * Get a project by its identifier
     * 
     * @param id Identifier of the project to get
     * @return Found project
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get a project by its identifier", description = "Returns a project")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Project found"),
            @ApiResponse(responseCode = "404", description = "Project not found")
    })
    public ResponseEntity<?> getProjectById(@PathVariable Long id) {
        return projectService.getProjectById(id);
    }

    /**
     * Save a project
     * 
     * @param project Project data to save
     * @return Saved project
     */
    @PostMapping
    @Operation(summary = "Save a project", description = "Creates a project")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Project created")
    })
    public ResponseEntity<?> saveProject(@RequestBody CreateProjectRequest project) {
        return projectService.saveProject(project);
    }

    /**
     * Update a project
     * 
     * @param id      Identifier of the project to update
     * @param project Project data to update
     * @return Updated project
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update a project", description = "Updates a project")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Project updated"),
            @ApiResponse(responseCode = "404", description = "Project not found")
    })
    public ResponseEntity<?> updateProject(@PathVariable Long id, @RequestBody CreateProjectRequest project) {
        return projectService.updateProject(id, project);
    }

    /**
     * Delete a project
     * 
     * @param id Identifier of the project to delete
     * @return Deleted project
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a project", description = "Deletes a project")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Project deleted"),
            @ApiResponse(responseCode = "404", description = "Project not found")
    })
    public ResponseEntity<?> deleteProject(@PathVariable Long id) {
        return projectService.deleteProject(id);
    }

    /**
     * Delete all projects
     * 
     * @return Deleted projects
     */
    @DeleteMapping
    @Operation(summary = "Delete all projects", description = "Deletes all projects")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Projects deleted")
    })
    public ResponseEntity<?> deleteAllProjects() {
        return projectService.deleteAllProjects();
    }
}

