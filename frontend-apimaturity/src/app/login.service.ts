import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators'; // Import tap operator
import { Router } from '@angular/router'; // Import Router

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private loginUrl = 'http://localhost:8080/api/apimaturity/login'; // API endpoint

  constructor(private http: HttpClient, private router: Router) { } // Inject Router

  userLogin(credentials: any): Observable<any> {
    return this.http.post(this.loginUrl, credentials, { responseType: 'json' }) // Change responseType to 'json' to handle JSON response
      .pipe(
        tap((response: any) => {
          localStorage.setItem('access_token', response.token); 
          this.router.navigate(['/']); 
        }),
        catchError((error: HttpErrorResponse) => {
          if (error.status === 401) {
            // Handle 401 error
            this.router.navigate(['/login']); // Redirect to login page
          }
          // Re-throw the error so that the calling component can handle it if needed
          return throwError(error);
        })
      );
  }
}