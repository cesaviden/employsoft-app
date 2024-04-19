import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../../environments/enviroment';
import { User } from '../../models/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  getAllUsers() {
    return this.http.get<User[]>(environment.urlApi + '/users');
  }

  getUserById(id: number) {
    return this.http.get<User>(environment.urlApi + '/users/' + id);
  }

  getUserByJwt() {
    return this.http.get<User>(environment.urlApi + '/users/jwt');
  }

  updateUser(user: any) {
    return this.http.put<User>(environment.urlApi + '/users/' + user.id, user);
  }
}
