package com.app.employsoft.api.mappers.implementations;

import java.util.HashSet;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.app.employsoft.api.dto.CreateProjectRequest;
import com.app.employsoft.api.dto.ProjectDTO;
import com.app.employsoft.api.dto.TaskDTO;
import com.app.employsoft.api.entities.Project;
import com.app.employsoft.api.mappers.interfaces.ProjectMapper;
import com.app.employsoft.api.repositories.TaskDAO;
import com.app.employsoft.auth.dto.UserDTO;
import com.app.employsoft.auth.entities.UserEntity;
import com.app.employsoft.auth.repositories.UserDAO;

@Service
public class ProjectMapperImpl implements ProjectMapper {

    private UserDAO userDAO;
    private TaskDAO taskDAO;

    public ProjectMapperImpl(UserDAO userDAO, TaskDAO taskDAO) {
        this.userDAO = userDAO;
        this.taskDAO = taskDAO;
    }

    @Override
    public Project toProject(CreateProjectRequest createProjectRequest) {

        return new Project(
                createProjectRequest.id(),
                createProjectRequest.name(),
                createProjectRequest.description(),
                createProjectRequest.startDate(),
                createProjectRequest.estimatedEndDate(),
                createProjectRequest.status(),
                userDAO.findById(createProjectRequest.supervisorId()).get(),
                (new HashSet<>(userDAO.findAllById(createProjectRequest.assignedEmployeesId()))),
                (new HashSet<>(taskDAO.findAllById(createProjectRequest.tasksId()))));
    }

    @Override
    public ProjectDTO toProjectDto(Project project) {

        UserEntity supervisorEntity = project.getSupervisor();
        UserDTO supervisor = new UserDTO(supervisorEntity.getId(),
                supervisorEntity.getName(),
                supervisorEntity.getSurname(),
                supervisorEntity.getUsername(),
                supervisorEntity.getEmail());

        HashSet<TaskDTO> tasks = new HashSet<>(project.getTasks()
                .stream()
                .map(task -> new TaskDTO(task.getId(),
                        task.getTitle(),
                        task.getDescription(),
                        task.getCreationDate(),
                        task.getDueDate(),
                        task.getStatus(),
                        task.getPriority(),
                        new UserDTO(task.getSupervisor().getId(),
                                task.getSupervisor().getName(),
                                task.getSupervisor().getSurname(),
                                task.getSupervisor().getUsername(),
                                task.getSupervisor().getEmail()),
                        new UserDTO(task.getEmployee().getId(),
                                task.getEmployee().getName(),
                                task.getEmployee().getSurname(),
                                task.getEmployee().getUsername(),
                                task.getEmployee().getEmail())))
                .collect(Collectors.toList()));

        HashSet<UserDTO> assignedEmployees = new HashSet<>(project.getAssignedEmployees().stream()
                .map(user -> new UserDTO(user.getId(),
                        user.getName(),
                        user.getSurname(),
                        user.getUsername(),
                        user.getEmail()))
                .collect(Collectors.toList()));

        return new ProjectDTO(
                project.getId(),
                project.getName(),
                project.getDescription(),
                project.getStartDate(),
                project.getEstimatedEndDate(),
                project.getStatus(),
                supervisor,
                assignedEmployees,
                tasks);
    }
}
