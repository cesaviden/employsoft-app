package com.app.employsoft.api.mappers.interfaces;

import com.app.employsoft.api.dto.CreateTaskRequest;
import com.app.employsoft.api.dto.TaskDTO;
import com.app.employsoft.api.entities.Task;

public interface TaskMapper {

    Task toTask(CreateTaskRequest taskDTO);

    TaskDTO toTaskDto(Task task);
}
