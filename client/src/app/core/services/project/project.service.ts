import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../../environments/enviroment';
import { Project } from '../../models/project';
import { CreateProject } from '../../models/createProject';

@Injectable({
  providedIn: 'root',
})
export class ProjectService {

  constructor(private http: HttpClient) {}

  getAllProjects() {
    return this.http.get<Project[]>(environment.urlApi + '/projects');
  }

  getProjectById(id: number) {
    return this.http.get<Project>(environment.urlApi + '/projects/' + id);
  }

  createProject(project: CreateProject) {
    return this.http.post<Project>(environment.urlApi + '/projects', project);
  }

  updateProject(project: Project) {
    return this.http.put<Project>(environment.urlApi + '/projects/' + project.id, project);
  }

  deleteProject(id: number) {
    return this.http.delete<Project>(environment.urlApi + '/projects/' + id);
  }

  getProjectsBySupervisor(username: string) {
    return this.http.get<Project[]>(environment.urlApi + '/projects/supervisor/' + username);
  }

  getProjectsByEmployee(name: string) {
    return this.http.get<Project[]>(environment.urlApi + '/projects/employee/' + name);
  }
}
