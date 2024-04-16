package com.app.employsoft.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.employsoft.api.dto.CreateTaskRequest;
import com.app.employsoft.api.dto.TaskDTO;
import com.app.employsoft.api.services.implementations.TaskServiceImpl;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

    private TaskServiceImpl taskService;

    public TaskController(TaskServiceImpl taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<?> getAllTasks() {
        return ResponseEntity.ok().body(taskService.getAllTasks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTaskById(@PathVariable Long id) {
        return ResponseEntity.ok().body(taskService.getTaskById(id));
    }

    @PostMapping
    public ResponseEntity<?> saveTask(@RequestBody CreateTaskRequest task) {
        return ResponseEntity.ok().body(taskService.saveTask(task));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTask(@PathVariable Long id, @RequestBody CreateTaskRequest task) {
        return ResponseEntity.ok().body(taskService.updateTask(id, task));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        return taskService.deleteTask(id);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAllTasks() {
        return taskService.deleteAllTasks();
    }
}
