import { Task } from './task';
import { User } from './user';

export interface Project {
  id: number;
  name: string;
  description: string;
  startDate: Date;
  estimatedEndDate: Date;
  status: string;
  supervisor: User;
  assignedEmployees: User[];
  tasks: Task[];
}
