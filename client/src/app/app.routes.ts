import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';

export const routes: Routes = [
  {
    path: '',
    loadChildren: () =>
      import('./pages/auth/auth.routes').then((m) => m.authRoutes),
  },

  {
    path: '',
    loadChildren: () =>
      import('./pages/employees/employee.routes').then((m) => m.employeeRoutes),
  },

  {
    path: '',
    loadChildren: () =>
      import('./pages/supervisor/supervisor.routes').then(
        (m) => m.supervisorRoutes
      ),
  },

  {
    path: 'home',
    component: HomeComponent,
  },

  {
    path: 'profile',
    component: HomeComponent,
  },
];
