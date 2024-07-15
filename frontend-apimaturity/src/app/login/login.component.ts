import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http'; 
import { LoginService } from '../login.service';
import { MatLegacySnackBar as MatSnackBar } from '@angular/material/legacy-snack-bar';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  credentials: any = {};

  constructor(
    private snackBar: MatSnackBar,
    private http: HttpClient, // Inject HttpClient
    private router: Router, // Inject Router
    private loginService: LoginService
  ) { }

  ngOnInit(): void {
  }

  login() {
    this.loginService.userLogin(this.credentials)
      .subscribe({
        next: (loginResponse: any) => {
          console.log(loginResponse);
          this.router.navigate(['/clients']); // Redirect to the client app
        },
        error: (error: any) => {
          console.log(error);
          if (error.status === 401) {
            this.snackBar.open('Unauthorized: Invalid username or password', 'Close', {
              duration: 3000,
            });
          } else {
            this.snackBar.open('An error occurred. Please try again later.', 'Close', {
              duration: 3000,
            });
          }
        }
      });
  }
}
