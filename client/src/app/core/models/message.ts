import { User } from './user';

export interface Message {
  id: number;
  content: string;
  creationDate: string;
  sender: User;
}
