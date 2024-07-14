import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(private router: Router) {}

  canActivate(): boolean {
    if (localStorage.getItem('access_token')) {
      // If the token exists, return true to allow the navigation
      return true;
    } else {
      // If the token doesn't exist, redirect to the login page and return false
      this.router.navigate(['/login']);
      return false;
    }
  }
}