package com.app.employsoft;

import com.app.employsoft.api.entities.Message;
import com.app.employsoft.api.entities.Project;
import com.app.employsoft.api.entities.Task;
import com.app.employsoft.api.entities.enums.ProjectStatus;
import com.app.employsoft.api.entities.enums.TaskPriority;
import com.app.employsoft.api.entities.enums.TaskStatus;
import com.app.employsoft.api.repositories.MessageDAO;
import com.app.employsoft.api.repositories.ProjectDAO;
import com.app.employsoft.api.repositories.TaskDAO;
import com.app.employsoft.auth.entities.PermissionEntity;
import com.app.employsoft.auth.entities.RoleEntity;
import com.app.employsoft.auth.entities.RoleEnum;
import com.app.employsoft.auth.entities.UserEntity;
import com.app.employsoft.auth.repositories.UserDAO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class EmploySoftAppApplication {

        public static void main(String[] args) {
                SpringApplication.run(EmploySoftAppApplication.class, args);
        }

        @SuppressWarnings("null")
        @Bean
        CommandLineRunner init(UserDAO userDAO, TaskDAO taskDAO, ProjectDAO projectDAO, MessageDAO messageDAO) {

                return args -> {
                        /* Create PERMISSIONS */
                        PermissionEntity createPermission = PermissionEntity.builder()
                                        .name("CREATE")
                                        .build();

                        PermissionEntity readPermission = PermissionEntity.builder()
                                        .name("READ")
                                        .build();

                        PermissionEntity updatePermission = PermissionEntity.builder()
                                        .name("UPDATE")
                                        .build();

                        PermissionEntity deletePermission = PermissionEntity.builder()
                                        .name("DELETE")
                                        .build();

                        PermissionEntity checkPermission = PermissionEntity.builder()
                                        .name("CHECK")
                                        .build();

                        PermissionEntity writePermission = PermissionEntity.builder()
                                        .name("WRITE")
                                        .build();

                        /* Create ROLES */
                        RoleEntity roleSupervisor = RoleEntity.builder()
                                        .roleEnum(RoleEnum.SUPERVISOR)
                                        .permissionList(Set.of(createPermission, readPermission, checkPermission,
                                                        writePermission, updatePermission,
                                                        deletePermission))
                                        .build();

                        RoleEntity roleUser = RoleEntity.builder()
                                        .roleEnum(RoleEnum.USER)
                                        .permissionList(Set.of(readPermission))
                                        .build();

                        RoleEntity roleEmployee = RoleEntity.builder()
                                        .roleEnum(RoleEnum.EMPLOYEE)
                                        .permissionList(Set.of(readPermission, checkPermission, writePermission))
                                        .build();

                        /* CREATE USERS */
                        UserEntity supervisorSantiago = UserEntity.builder()
                                        .username("santiago")
                                        .password("$2a$10$cMY29RPYoIHMJSuwRfoD3eQxU1J5Rww4VnNOUOAEPqCBshkNfrEf6")
                                        .email("santiago@vg.com")
                                        .name("Santiago")
                                        .surname("Abascal")
                                        .isEnabled(true)
                                        .accountNoExpired(true)
                                        .accountNoLocked(true)
                                        .credentialNoExpired(true)
                                        .roles(Set.of(roleSupervisor))
                                        .build();

                        UserEntity userDaniel = UserEntity.builder()
                                        .username("daniel")
                                        .password("$2a$10$cMY29RPYoIHMJSuwRfoD3eQxU1J5Rww4VnNOUOAEPqCBshkNfrEf6")
                                        .email("daniel@vg.com")
                                        .name("Daniel")
                                        .surname("Martinez")
                                        .isEnabled(true)
                                        .accountNoExpired(true)
                                        .accountNoLocked(true)
                                        .credentialNoExpired(true)
                                        .roles(Set.of(roleUser))
                                        .build();

                        UserEntity employeeAndrea = UserEntity.builder()
                                        .username("andrea")
                                        .password("$2a$10$cMY29RPYoIHMJSuwRfoD3eQxU1J5Rww4VnNOUOAEPqCBshkNfrEf6")
                                        .email("andrea@vg.com")
                                        .name("Andrea")
                                        .surname("Gonzalez")
                                        .isEnabled(true)
                                        .accountNoExpired(true)
                                        .accountNoLocked(true)
                                        .credentialNoExpired(true)
                                        .roles(Set.of(roleEmployee))
                                        .build();

                        UserEntity employeeAnyi = UserEntity.builder()
                                        .username("anyi")
                                        .password("$2a$10$cMY29RPYoIHMJSuwRfoD3eQxU1J5Rww4VnNOUOAEPqCBshkNfrEf6")
                                        .email("anyi@vg.com")
                                        .name("Anyi")
                                        .surname("Marrero")
                                        .isEnabled(true)
                                        .accountNoExpired(true)
                                        .accountNoLocked(true)
                                        .credentialNoExpired(true)
                                        .roles(Set.of(roleEmployee))
                                        .build();

                        /* CREATE TASKS */

                        LocalDate today = LocalDate.now();

                        Task task1 = Task.builder()
                                        .title("Task 1")
                                        .description("Task 1 description")
                                        .creationDate(today)
                                        .dueDate(today.plusDays(2))
                                        .status(TaskStatus.COMPLETED)
                                        .priority(TaskPriority.HIGH)
                                        .supervisor(supervisorSantiago)
                                        .employee(employeeAndrea)
                                        .build();

                        Task task2 = Task.builder()
                                        .title("Task 2")
                                        .description("Task 2 description")
                                        .creationDate(today)
                                        .dueDate(today.plusDays(2))
                                        .status(TaskStatus.PENDING)
                                        .priority(TaskPriority.LOW)
                                        .supervisor(supervisorSantiago)
                                        .employee(employeeAnyi)
                                        .build();

                        Task task3 = Task.builder()
                                        .title("Task 3")
                                        .description("Task 3 description")
                                        .creationDate(today)
                                        .dueDate(today.plusDays(2))
                                        .status(TaskStatus.IN_PROGRESS)
                                        .priority(TaskPriority.MEDIUM)
                                        .supervisor(supervisorSantiago)
                                        .employee(employeeAnyi)
                                        .build();

                        Task task4 = Task.builder()
                                        .title("Task 4")
                                        .description("Task 4 description")
                                        .creationDate(today)
                                        .dueDate(today.plusDays(2))
                                        .status(TaskStatus.PENDING)
                                        .priority(TaskPriority.LOW)
                                        .supervisor(supervisorSantiago)
                                        .employee(employeeAndrea)
                                        .build();

                        /* CREATE PROJECTS */

                        Project project1 = Project.builder()
                                        .name("Project 1")
                                        .description("Project 1 description")
                                        .startDate(today)
                                        .estimatedEndDate(today.plusMonths(2))
                                        .status(ProjectStatus.COMPLETED)
                                        .assignedEmployees(Set.of(employeeAndrea, employeeAnyi))
                                        .supervisor(supervisorSantiago)
                                        .tasks(Set.of(task1, task2))
                                        .build();

                        Project project2 = Project.builder()
                                        .name("Project 2")
                                        .description("Project 2 description")
                                        .startDate(today)
                                        .estimatedEndDate(today.plusMonths(2))
                                        .status(ProjectStatus.IN_PROGRESS)
                                        .assignedEmployees(Set.of(employeeAndrea, employeeAnyi))
                                        .supervisor(supervisorSantiago)
                                        .tasks(Set.of(task3, task4))
                                        .build();

                        /* CREATE MESSAGES */

                        Message message1 = Message.builder()
                                        .content("Message 1 content")
                                        .sender(employeeAnyi)
                                        .build();

                        Message message2 = Message.builder()
                                        .content("Message 2 content")
                                        .sender(supervisorSantiago)
                                        .build();

                        Message message3 = Message.builder()
                                        .content("Message 3 content")
                                        .sender(employeeAndrea)
                                        .build();

                        /* SAVE USERS */
                        userDAO.saveAll(List.of(supervisorSantiago, userDaniel, employeeAndrea, employeeAnyi));

                        /* SAVE TASKS */
                        taskDAO.saveAll(List.of(task1, task2, task3, task4));

                        /* SAVE PROJECTS */
                        projectDAO.saveAll(List.of(project1, project2));

                        /* SAVE MESSAGES */
                        messageDAO.saveAll(List.of(message1, message2, message3));

                };
        }
}
