import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Project } from '../../../core/models/project';
import { AuthService } from '../../../core/services/auth/auth.service';
import { UserService } from '../../../core/services/user/user.service';
import { ProjectService } from '../../../core/services/project/project.service';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [ CommonModule, FormsModule],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent implements OnInit {

  constructor(private userService: UserService, private projectService: ProjectService) { }
  
  ngOnInit(): void {
    this.userService.getUserByJwt().subscribe(
      (data) => {
        this.projectService.getProjectsBySupervisor(data.username).subscribe(
          (data) => {
            console.log(data);
            this.projects = data;
          }
        );
      }
    );
  
  }

  projects: Project[];

}
