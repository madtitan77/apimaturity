import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component'; // Import the RegisterComponent
import { ClientsListComponent } from './clients-list/clients-list.component'; // Import the ClientsListComponent
import { ClientAddComponent } from './client-add/client-add.component'; // Import the ClientAddComponent
import { ClientEditComponent } from './client-edit/client-edit.component'; // Import the ClientEditComponent
import { ClientDetailComponent } from './client-detail/client-detail.component'; // Import the ClientDetailComponent

const routes: Routes = [
  { path: 'clients', component: ClientsListComponent },
  { path: 'clients/add', component: ClientAddComponent },
  { path: 'clients/edit/:id', component: ClientEditComponent },
  { path: 'clients/:id', component: ClientDetailComponent },
  { path: 'login', component: LoginComponent },
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'register', component: RegisterComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
