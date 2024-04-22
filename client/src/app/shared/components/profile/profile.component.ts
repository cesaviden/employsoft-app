import { Component, OnInit } from '@angular/core';
import { User } from '../../../core/models/user';
import { UserService } from '../../../core/services/user/user.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../../core/services/auth/auth.service';

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  user: User =
  {
    id: 0,
    name: '',
    surname: '',
    username: '',
    email: ''
  };

  constructor(private userService: UserService, authService: AuthService) {}

  ngOnInit(): void {
    this.userService.getUserByJwt().subscribe(((data: User) => {
      this.user = data;
    }));
  }

  updateUser(event: Event) {
    this.userService.updateUser(this.user).subscribe(
      (data) => {
        this.user = data;
      }
    );
  }
}