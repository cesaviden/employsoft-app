import { Routes } from '@angular/router';
import { HomeComponent } from './shared/components/home/home.component';
import { TemplateComponent } from './shared/components/layouts/template/template.component';
import { ProfileComponent } from './shared/components/profile/profile.component';
import { ChatComponent } from './shared/components/chat/chat.component';
import { StartComponent } from './shared/components/layouts/start/start.component';
import { authGuard } from './core/guards/auth.guard';
import { TasksProjectComponent } from './shared/components/tasksProjects/tasksproject.component';

export const routes: Routes = [
  {
    path: '',
    component: StartComponent,
  },
  {
    path: '',
    loadChildren: () =>
      import('./pages/auth/auth.routes').then((m) => m.authRoutes),
  },

  {
    path: '',
    component: TemplateComponent,
    children: [
      {
        path: 'home',
        component: HomeComponent,
        canActivate: [authGuard],
      },
      {
        path: '',
        loadChildren: () =>
          import('./pages/employees/employee.routes').then(
            (m) => m.employeeRoutes
          ),
        canActivate: [authGuard],
      },

      {
        path: '',
        loadChildren: () =>
          import('./pages/supervisor/supervisor.routes').then(
            (m) => m.supervisorRoutes
          ),
        canActivate: [authGuard],
      },
      {
        path: 'project/:id',
        component: TasksProjectComponent,
      },
      {
        path: 'profile',
        component: ProfileComponent,
        canActivate: [authGuard],
      },
      {
        path: 'chat',
        component: ChatComponent,
        canActivate: [authGuard],
      },
    ],
  },
];
