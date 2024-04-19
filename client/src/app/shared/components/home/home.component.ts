import { Component, OnInit } from '@angular/core';
import { ProjectService } from '../../../core/services/project/project.service';
import { TaskService } from '../../../core/services/task/task.service';
import { UserService } from '../../../core/services/user/user.service';
import { Project } from '../../../core/models/project';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [ CommonModule ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css',
})
export class HomeComponent implements OnInit {
  projects: Project[];

  constructor(
    private projectService: ProjectService,
    private taskService: TaskService,
    private userService: UserService,
    private router: Router) {}

    goToProject(project: Project) {
      this.router.navigate(['/project', project.id]);
      }
      
  ngOnInit(): void {
    this.projectService.getAllProjects().subscribe((data: Project[]) => {
      this.projects = data;      
      console.log(this.projects);
    });
  }
}
