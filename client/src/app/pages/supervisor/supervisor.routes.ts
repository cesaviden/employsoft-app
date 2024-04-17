import { Routes } from '@angular/router';
import { CalendarComponent } from './calendar/calendar.component';
import { DashboardComponent } from './dashboard/dashboard.component';

export const supervisorRoutes: Routes = [
    {
        path: 'calendar',
        component: CalendarComponent
    },
    {
        path: 'dashboard',
        component: DashboardComponent
    }
]