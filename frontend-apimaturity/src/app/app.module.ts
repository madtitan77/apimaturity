import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms'; // Import FormsModule here

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { HttpClientModule } from '@angular/common/http';
import { RegisterComponent } from './register/register.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { ReactiveFormsModule } from '@angular/forms';
// Step 2: Import MatSnackBarModule
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { JwtModule } from '@auth0/angular-jwt';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { JwtInterceptor } from './jwt.interceptor';
import { RouterModule, Routes } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { ClientsService } from './clients.service';

import { AssessmentGroupService } from './assessmentGroup.service';
import { AssessmentGroupAddComponent } from './assessment-group-add/assessment-group-add.component';



const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'clients', component: ClientsService }, // Assuming you have a ClientsComponent
  { path: '', redirectTo: '/login', pathMatch: 'full' }, // Redirect to login by default
  { path: 'register', component: RegisterComponent },
  { path: 'assessment-group', component: AssessmentGroupService }
  // Add other routes here
];

export function tokenGetter() {
  return localStorage.getItem('access_token');
}

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    AssessmentGroupAddComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(routes), 
    AppRoutingModule,
    FormsModule,  // Move FormsModule to imports
    HttpClientModule, BrowserAnimationsModule,
    MatInputModule,
    MatButtonModule,
    MatFormFieldModule,
    ReactiveFormsModule,
    MatIconModule,
    MatSnackBarModule,
    MatMenuModule,
    JwtModule.forRoot({
      config: {
        tokenGetter: tokenGetter,
        allowedDomains: ['localhost'], // Your API domain
        disallowedRoutes: ['http://example.com/examplebadroute/'],
      },
    }),    
  ],
  providers: [{ provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true }],
  bootstrap: [AppComponent],
  
})
export class AppModule { }
