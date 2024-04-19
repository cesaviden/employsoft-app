import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../../environments/enviroment';
import { Message } from '../../models/message';

@Injectable({
  providedIn: 'root',
})
export class MessageService {
  constructor(private http: HttpClient) {}

  getAllMessages() {
    return this.http.get<Message[]>(environment.urlApi + '/messages');
  }

  getMessageById(id: number) {
    return this.http.get<Message>(environment.urlApi + '/messages/' + id);
  }

  createMessage(message: any) {
    return this.http.post<Message>(environment.urlApi + '/messages', message);
  }

  deleteMessage(id: number) {
    return this.http.delete<Message>(environment.urlApi + '/messages/' + id);
  }

  updateMessage(message: any) {
    return this.http.put<Message>(
      environment.urlApi + '/messages/' + message.id,
      message
    );
  }

  getMessagebyUser(name: string) {
    return this.http.get<Message[]>(environment.urlApi + '/messages/user/' + name);
  }
}
