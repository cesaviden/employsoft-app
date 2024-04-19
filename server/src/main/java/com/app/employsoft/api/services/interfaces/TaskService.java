package com.app.employsoft.api.services.interfaces;

import org.springframework.http.ResponseEntity;

import com.app.employsoft.api.dto.CreateTaskRequest;
import com.app.employsoft.api.dto.TaskDTO;

public interface TaskService {

    ResponseEntity<?> getAllTasks();

    ResponseEntity<?> getTaskById(Long taskId);

    ResponseEntity<?> getTasksByProject(Long projectId);

    ResponseEntity<?> saveTask(CreateTaskRequest task);

    ResponseEntity<?> updateTask(Long taskId, CreateTaskRequest task);

    ResponseEntity<?> deleteTask(Long taskId);

    ResponseEntity<?> deleteAllTasks();
}
