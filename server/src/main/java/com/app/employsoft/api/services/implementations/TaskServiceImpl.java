package com.app.employsoft.api.services.implementations;

import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;

import com.app.employsoft.api.dto.CreateTaskRequest;
import com.app.employsoft.api.dto.TaskDTO;
import com.app.employsoft.api.entities.Task;
import com.app.employsoft.api.mappers.implementations.TaskMapperImpl;
import com.app.employsoft.api.repositories.TaskDAO;
import com.app.employsoft.api.services.interfaces.TaskService;

@Service
public class TaskServiceImpl implements TaskService {

    private TaskDAO taskDAO;
    private TaskMapperImpl taskMapper;

    public TaskServiceImpl(TaskDAO taskDAO, TaskMapperImpl taskMapper) {
        this.taskDAO = taskDAO;
        this.taskMapper = taskMapper;
    }

    @Override
    public ResponseEntity<?> getAllTasks() {
        try {
            List<Task> tasks = taskDAO.findAll();
            if (tasks.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No tasks found");
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(tasks.stream().map(taskMapper::toTaskDto).toList());
            }
        } catch (InternalServerError e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("The tasks could not be retrieved");
        }
    }

    @Override
    public ResponseEntity<?> getTaskById(Long taskId) {
        try {

            Optional<Task> task = taskDAO.findById(taskId);

            if (task.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK)
                        .body(taskMapper.toTaskDto(task.get()));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found");
            }
        } catch (InternalServerError e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("The task could not be retrieved");
        }
    }

    @Override
    public ResponseEntity<?> saveTask(CreateTaskRequest task) {
        try {

            Task newTask = taskDAO.save(taskMapper.toTask(task));
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(taskMapper.toTaskDto(newTask));
        } catch (InternalServerError e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("The task could not be created");
        }
    }

    @Override
    public ResponseEntity<?> updateTask(Long taskId, CreateTaskRequest task) {
        try {
            Optional<Task> taskToUpdate = taskDAO.findById(taskId);
            if (taskToUpdate.isPresent()) {
                Task updatedTask = taskDAO.save(taskMapper.toTask(task));
                return ResponseEntity.status(HttpStatus.OK).body(taskMapper.toTaskDto(updatedTask));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task with id " + taskId + " not found");
            }
        } catch (InternalServerError e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("The task with id " + taskId + " could not be updated");
        }
    }

    @Override
    public ResponseEntity<?> deleteTask(Long taskId) {
        try {
            Optional<Task> taskToDelete = taskDAO.findById(taskId);
            if (taskToDelete.isPresent()) {
                taskDAO.delete(taskToDelete.get());
                return ResponseEntity.status(HttpStatus.OK).body("Task deleted successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task with id " + taskId + " not found");
            }
        } catch (InternalServerError e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("The task with id " + taskId + " could not be deleted");
        }
    }

    @Override
    public ResponseEntity<?> deleteAllTasks() {
        try {
            taskDAO.deleteAll();
            if (taskDAO.count() == 0) {
                return ResponseEntity.status(HttpStatus.OK).body("All tasks deleted successfully");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("The tasks cant be deleted");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("The tasks could not be deleted");
        }
    }
}
