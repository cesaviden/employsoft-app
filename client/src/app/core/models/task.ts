import { User } from './user';

export interface Task {
  id: number;
  title: string;
  description: string;
  creationDate: Date;
  dueDate: Date;
  priority: string;
  status: string;
  supervisor: User;
  employee: User;
}
