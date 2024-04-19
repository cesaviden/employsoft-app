import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../../environments/enviroment';
import { Task } from '../../models/task';

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  constructor(private http: HttpClient) { }

  getAllTasks() {
    return this.http.get<Task[]>(environment.urlApi + '/tasks');
  }

  getTaskById(id: number) {
    return this.http.get<Task>(environment.urlApi + '/tasks/' + id);
  }

  createTask(task: any) {
    return this.http.post<Task>(environment.urlApi + '/tasks', task);
  }

  updateTask(task: any) {
    return this.http.put<Task>(environment.urlApi + '/tasks/' + task.id, task);
  }

  deleteTask(id: number) {
    return this.http.delete<Task>(environment.urlApi + '/tasks/' + id);
  }

  getTasksByProject(id: string) {
    return this.http.get<Task[]>(environment.urlApi + '/tasks/project/' + id);
  }

  getTasksByEmployee(name: string) {
    return this.http.get<Task[]>(environment.urlApi + '/tasks/employee/' + name);
  }

  getTasksBySupervisor(name: string) {
    return this.http.get<Task[]>(environment.urlApi + '/tasks/supervisor/' + name);
  }
}
