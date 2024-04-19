package com.app.employsoft.api.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.app.employsoft.api.dto.CreateTaskRequest;
import com.app.employsoft.api.dto.TaskDTO;
import com.app.employsoft.api.services.implementations.TaskServiceImpl;

/**
 * Task controller
 */
@RestController
@RequestMapping("/api/v1/tasks")
@Tag(name = "Task Controller", description = "Controller for task management")
public class TaskController {

    private TaskServiceImpl taskService;

    public TaskController(TaskServiceImpl taskService) {
        this.taskService = taskService;
    }

    /**
     * Returns a list of all tasks
     *
     * @return list of tasks
     */
    @GetMapping
    @Operation(summary = "Returns a list of all tasks", description = "Returns a list of all tasks")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of tasks found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = TaskDTO[].class))
            })
    })
    public ResponseEntity<?> getAllTasks() {
        return ResponseEntity.ok().body(taskService.getAllTasks());
    }

    /**
     * Returns the task with the specified identifier
     *
     * @param id identifier of the task
     * @return task
     */
    @GetMapping("/{id}")
    @Operation(summary = "Returns the task with the specified identifier", description = "Returns the task with the specified identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = TaskDTO.class))
            }),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    public ResponseEntity<?> getTaskById(@PathVariable Long id) {
        return ResponseEntity.ok().body(taskService.getTaskById(id));
    }

    @GetMapping("/project/{id}")
    @Operation(summary = "Returns a list of tasks by project", description = "Returns a list of tasks by project")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of tasks found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = TaskDTO[].class))
            }),
            @ApiResponse(responseCode = "404", description = "Project not found")
    })
    public ResponseEntity<?> getTasksByProject(@PathVariable Long id) {
        return ResponseEntity.ok().body(taskService.getTasksByProject(id));
    }

    /**
     * Creates a new task
     *
     * @param task task to create
     * @return created task
     */
    @PostMapping
    @Operation(summary = "Creates a new task", description = "Creates a new task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Task created", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = TaskDTO.class))
            })
    })
    public ResponseEntity<?> saveTask(@RequestBody CreateTaskRequest task) {
        return ResponseEntity.status(201).body(taskService.saveTask(task));
    }

    /**
     * Updates the task with the specified identifier
     *
     * @param id identifier of the task
     * @param task task with the new data
     * @return updated task
     */
    @PutMapping("/{id}")
    @Operation(summary = "Updates the task with the specified identifier", description = "Updates the task with the specified identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task updated", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = TaskDTO.class))
            }),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    public ResponseEntity<?> updateTask(@PathVariable Long id, @RequestBody CreateTaskRequest task) {
        return ResponseEntity.ok().body(taskService.updateTask(id, task));
    }

    /**
     * Deletes the task with the specified identifier
     *
     * @param id identifier of the task
     * @return
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletes the task with the specified identifier", description = "Deletes the task with the specified identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task deleted"),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        return taskService.deleteTask(id);
    }

    /**
     * Deletes all tasks
     *
     * @return
     */
    @DeleteMapping
    @Operation(summary = "Deletes all tasks", description = "Deletes all tasks")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tasks deleted")
    })
    public ResponseEntity<?> deleteAllTasks() {
        return taskService.deleteAllTasks();
    }
}


