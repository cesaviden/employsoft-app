import { Component } from '@angular/core';
import { Project } from '../../../core/models/project';
import { ProjectService } from '../../../core/services/project/project.service';
import { UserService } from '../../../core/services/user/user.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-projects',
  standalone: true,
  imports: [ CommonModule, FormsModule],
  templateUrl: './projects.component.html',
  styleUrl: './projects.component.css'
})
export class ProjectsComponent {

  projects:Project[];

  constructor(private projectService: ProjectService, private userService: UserService){}

  ngOnInit(): void {

    this.userService.getUserByJwt().subscribe((data) => {
      this.projectService
        .getProjectsByEmployee(data.username)
        .subscribe((data) => {
          this.projects = data;
        });
    });
  }
}
