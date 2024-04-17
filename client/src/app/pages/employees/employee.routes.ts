import { Routes } from '@angular/router';
import { ProjectsComponent } from './projects/projects.component';
import { TasksComponent } from './tasks/tasks.component';

export const employeeRoutes: Routes = [
    {
        path: 'projects',
        component: ProjectsComponent
    },
    {
        path: 'tasks',
        component: TasksComponent
    }
]