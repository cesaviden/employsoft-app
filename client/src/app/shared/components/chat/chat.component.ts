import { Component, OnInit } from '@angular/core';
import { MessageService } from '../../../core/services/message/message.service';
import { Message } from '../../../core/models/message';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { UserService } from '../../../core/services/user/user.service';

@Component({
  selector: 'app-chat',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './chat.component.html',
  styleUrl: './chat.component.css'
})
export class ChatComponent implements OnInit {
  
  messageText: string;
  messages:Message[];

  constructor(private messageService: MessageService, private userService: UserService) { }

  ngOnInit(): void {

    this.messageService.getAllMessages().subscribe((data: Message[]) => {
      this.messages = data;
      console.log(this.messages);
    })
    }

    sendMessage() {
      this.userService.getUserByJwt().subscribe((data) => {
        this.messageService.createMessage({
          id: 0,
          creationDate: new Date(),
          content: this.messageText,
          senderId: data.id
        }).subscribe((data) => {
          this.messageText = '';
          this.messageService.getAllMessages().subscribe((data: Message[]) => {
            this.messages = data;
            console.log(this.messages);
          })
        })
      })
    }
}
