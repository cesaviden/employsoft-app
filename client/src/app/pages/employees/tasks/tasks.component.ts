import { Component, OnInit } from '@angular/core';
import { Task } from '../../../core/models/task';

@Component({
  selector: 'app-tasks',
  standalone: true,
  imports: [],
  templateUrl: './tasks.component.html',
  styleUrl: './tasks.component.css',
})
export class TasksComponent implements OnInit {
  constructor() {}

  tasks:Task[];

  ngOnInit(): void {}
}
