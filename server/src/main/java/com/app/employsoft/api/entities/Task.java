package com.app.employsoft.api.entities;

import java.time.LocalDate;
import java.util.Set;
import com.app.employsoft.api.entities.enums.TaskPriority;
import com.app.employsoft.api.entities.enums.TaskStatus;
import com.app.employsoft.auth.entities.UserEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tasks")

public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Temporal(TemporalType.DATE)
    @Column(name = "creation_date", nullable = false)
    private LocalDate creationDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskPriority priority;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskStatus status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "supervisor_id", nullable = false)
    private UserEntity supervisor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id", nullable = false)
    private UserEntity employee;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "task_project", joinColumns = @JoinColumn(name = "task_id"), inverseJoinColumns = @JoinColumn(name = "project_id"))
    private Set<Project> projects;

}
