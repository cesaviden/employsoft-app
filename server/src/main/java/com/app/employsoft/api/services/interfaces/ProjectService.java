package com.app.employsoft.api.services.interfaces;

import org.springframework.http.ResponseEntity;
import com.app.employsoft.api.dto.CreateProjectRequest;

public interface ProjectService {


    ResponseEntity<?> getAllProjects();

    ResponseEntity<?> getProjectById(Long projectId);

    ResponseEntity<?> saveProject(CreateProjectRequest project);
    
    ResponseEntity<?> updateProject(Long projectId, CreateProjectRequest project);

    ResponseEntity<?> deleteProject(Long projectId);

    ResponseEntity<?> deleteAllProjects();



}
