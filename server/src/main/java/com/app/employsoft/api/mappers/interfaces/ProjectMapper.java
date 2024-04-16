package com.app.employsoft.api.mappers.interfaces;

import com.app.employsoft.api.dto.CreateProjectRequest;
import com.app.employsoft.api.dto.ProjectDTO;
import com.app.employsoft.api.entities.Project;

/**
 * ProjectMapper
 */
public interface ProjectMapper {

    Project toProject(CreateProjectRequest createProjectRequest);

    ProjectDTO toProjectDto(Project project);
}