import { Component, OnInit } from '@angular/core';
import { Task } from '../../../core/models/task';
import { TaskService } from '../../../core/services/task/task.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-tasks',
  standalone: true,
  imports: [],
  templateUrl: './tasksproject.component.html',
  styleUrl: './tasksproject.component.css',
})
export class TasksProjectComponent implements OnInit {
  tasks: Task[];

  constructor(
    private taskService: TaskService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id != null) {
      this.taskService.getTasksByProject(id).subscribe((data: Task[]) => {
        this.tasks = data;
        console.log(this.tasks);     
      });
    }
  }
  }
