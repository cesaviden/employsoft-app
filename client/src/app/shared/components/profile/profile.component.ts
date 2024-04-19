import { Component, OnInit } from '@angular/core';
import { User } from '../../../core/models/user';
import { UserService } from '../../../core/services/user/user.service';

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})
export class ProfileComponent implements OnInit {
user: User;

  constructor(private userService: UserService) {}

  ngOnInit(): void {
    this.userService.getUserByJwt().subscribe(((data: User) => {
      this.user = data;
    }));
  }
}

