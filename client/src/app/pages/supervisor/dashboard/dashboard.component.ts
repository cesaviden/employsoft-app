import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Project } from '../../../core/models/project';
import { AuthService } from '../../../core/services/auth/auth.service';
import { UserService } from '../../../core/services/user/user.service';
import { ProjectService } from '../../../core/services/project/project.service';
import { User } from '../../../core/models/user';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css',
})
export class DashboardComponent implements OnInit {
  name: string;
  description: string;
  estimatedEndDate: Date;
  selectedEmployees: string[];
  employees: User[];
  projects: Project[];
  supervisor: User;

  constructor(
    private userService: UserService,
    private projectService: ProjectService
  ) {}

  addProject($event: Event) {
    console.log(this.selectedEmployees);
    
    this.projectService
      .createProject({
        id: 0,
        name: this.name,
        description: this.description,
        status: 'IN_PROGRESS',
        startDate: new Date(),
        supervisorId: this.supervisor.id,
        estimatedEndDate: this.estimatedEndDate,
        assignedEmployeesId: this.selectedEmployees,
        tasksId: [],
      })
      .subscribe((data) => {
        this.projects.push(data);
      });
  }

  deleteProject(id: number) {
      this.projectService.deleteProject(id).subscribe((data) => {
        this.projects = this.projects.filter((project) => project.id !== id);
      });
    }

  ngOnInit(): void {
    this.userService.getUserByJwt().subscribe((data) => {
      this.projectService
        .getProjectsBySupervisor(data.username)
        .subscribe((data) => {
          console.log(data);
          this.projects = data;
        });
    });

    this.userService.getUserByJwt().subscribe((data) => {
      this.supervisor = data;
    });

    this.userService.getAllUsers().subscribe((data) => {
      this.employees = data;
    });
  }
}
