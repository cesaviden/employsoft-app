export interface CreateProject {
  id: number;
  name: string;
  description: string;
  startDate: Date;
  estimatedEndDate: Date;
  status: string;
  supervisorId: number;
  assignedEmployeesId: string[];
  tasksId: string[];
}
