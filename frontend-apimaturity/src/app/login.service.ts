import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Router } from '@angular/router'; // Import Router

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private loginUrl = 'http://localhost:8080/api/apimaturity/login'; // API endpoint

  constructor(private http: HttpClient, private router: Router) { } // Inject Router

  userLogin(credentials: any): Observable<any> {
    return this.http.post(this.loginUrl, credentials).pipe(
      catchError((error: HttpErrorResponse) => {
        if (error.status === 401) {
          // Handle 401 error
          // For example, redirect to login page or show an error message
          this.router.navigate(['/login']); // Redirect to login page
          // Alternatively, you could show an error message to the user
        }
        // Re-throw the error so that the calling component can handle it if needed
        return throwError(error);
      })
    );
  }
}