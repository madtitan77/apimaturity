import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) { }

  userLogin(credentials: any) {
    return this.http.post('/api/apimaturity/login', credentials);
  }
}
