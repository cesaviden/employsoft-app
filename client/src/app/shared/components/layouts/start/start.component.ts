import { Component, OnInit } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { BehaviorSubject } from 'rxjs';
import { AuthService } from '../../../../core/services/auth/auth.service';

@Component({
  selector: 'app-start',
  standalone: true,
  imports: [RouterModule],
  templateUrl: './start.component.html',
  styleUrl: './start.component.css',
})
export class StartComponent implements OnInit {
  constructor(private router: Router, private authService: AuthService) {}

  isAuthenticated$: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(
    false
  );
  isAuthenticated: boolean = false;

  ngOnInit(): void {

    this.isAuthenticated = sessionStorage.getItem('JWT_TOKEN') !== null;

    if (this.isAuthenticated) {
      this.router.navigate(['/home']);
    }
  }
}
