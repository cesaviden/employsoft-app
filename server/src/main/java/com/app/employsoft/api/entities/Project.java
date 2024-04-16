package com.app.employsoft.api.entities;

import java.time.LocalDate;
import java.util.Set;
import com.app.employsoft.api.entities.enums.ProjectStatus;
import com.app.employsoft.auth.entities.UserEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "projects")

public class Project {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(unique = true, nullable = false)
        private String name;

        @Column(nullable = false)
        private String description;

        @Temporal(TemporalType.DATE)
        @Column(name = "start_date", nullable = false)
        private LocalDate startDate;

        @Temporal(TemporalType.DATE)
        @Column(name = "estimated_end_date", nullable = false)
        private LocalDate estimatedEndDate;

        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        private ProjectStatus status;

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "supervisor_id", nullable = false)
        private UserEntity supervisor;

        @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable(name = "project_employee", joinColumns = @JoinColumn(name = "project_id"), inverseJoinColumns = @JoinColumn(name = "employee_id"))
        private Set<UserEntity> assignedEmployees;

        @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable(name = "task_project", joinColumns = @JoinColumn(name = "project_id"), inverseJoinColumns = @JoinColumn(name = "task_id"))
        private Set<Task> tasks;
}
