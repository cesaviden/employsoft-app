package com.app.employsoft.api.mappers.implementations;

import java.util.HashSet;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.app.employsoft.api.dto.CreateTaskRequest;
import com.app.employsoft.api.dto.TaskDTO;
import com.app.employsoft.api.entities.Task;
import com.app.employsoft.api.mappers.interfaces.TaskMapper;
import com.app.employsoft.api.repositories.ProjectDAO;
import com.app.employsoft.auth.dto.UserDTO;
import com.app.employsoft.auth.entities.UserEntity;
import com.app.employsoft.auth.repositories.UserDAO;

/**
 * TaskMapper
 */

@Service
public class TaskMapperImpl implements TaskMapper {

    private UserDAO userDAO;
    private ProjectDAO projectDAO;

    public TaskMapperImpl(UserDAO userDAO, ProjectDAO projectDAO) {
        this.userDAO = userDAO;
        this.projectDAO = projectDAO;
    }

    @Override
    public Task toTask(CreateTaskRequest taskDTO) {

        return new Task(
                null,
                taskDTO.title(),
                taskDTO.description(),
                taskDTO.creationDate(),
                taskDTO.dueDate(),
                taskDTO.priority(),
                taskDTO.status(),
                userDAO.findById(taskDTO.supervisorId()).get(),
                userDAO.findById(taskDTO.employeeId()).get(),
                (new HashSet<>(projectDAO.findAllById(taskDTO.projectsId()))));
    }

    @Override
    public TaskDTO toTaskDto(Task task) {

        UserEntity supervisorEntity = task.getSupervisor();
        UserEntity employeeEntity = task.getEmployee();

        UserDTO supervisor = new UserDTO(
                supervisorEntity.getId(),
                supervisorEntity.getName(),
                supervisorEntity.getSurname(),
                supervisorEntity.getUsername(),
                supervisorEntity.getEmail()
        );

        UserDTO employee = new UserDTO(
                employeeEntity.getId(),
                employeeEntity.getName(),
                employeeEntity.getSurname(),
                employeeEntity.getUsername(),
                employeeEntity.getEmail()
        );

        return new TaskDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getCreationDate(),
                task.getDueDate(),
                task.getStatus(),
                task.getPriority(),
                supervisor,
                employee);
    }
}