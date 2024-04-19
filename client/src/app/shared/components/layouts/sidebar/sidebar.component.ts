import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { AuthService } from '../../../../core/services/auth/auth.service';

@Component({
  selector: 'app-sidebar',
  standalone: true,
  imports: [ RouterModule ],
  templateUrl: './sidebar.component.html',
  styleUrl: './sidebar.component.css'
})
export class SidebarComponent implements OnInit {

  roleUser: string;

  constructor(private authService: AuthService) { }

  ngOnInit(): void { 

    this.authService.requestRole().subscribe((data: any) => {
      this.roleUser = data.role;
    })
  }

}
