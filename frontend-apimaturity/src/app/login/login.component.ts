import { Component, OnInit } from '@angular/core';
import { LoginService } from '../login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  credentials: any = {};

  constructor(private loginService: LoginService) { }

  ngOnInit(): void {
  }

  login() {
    this.loginService.userLogin(this.credentials).subscribe(
      (loginResponse: any) => console.log(loginResponse),
      (error: any) => console.log(error)
    );
  }
}
