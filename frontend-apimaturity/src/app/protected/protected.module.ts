import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProtectedRoutingModule } from './protected-routing.module';
import { ClientsListComponent } from '../clients-list/clients-list.component';
import { ClientAddComponent } from '../client-add/client-add.component';
import { ClientEditComponent } from '../client-edit/client-edit.component';
import { ClientDetailComponent } from '../client-detail/client-detail.component';
import { MatFormFieldModule } from '@angular/material/form-field';
import { FormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input'; 


@NgModule({
  declarations: [
    ClientsListComponent,
    ClientAddComponent,
    ClientEditComponent,
    ClientDetailComponent,
  ],
  imports: [
    CommonModule,
    ProtectedRoutingModule,
    MatFormFieldModule,   
    FormsModule, // Add FormsModule here
    MatInputModule // Add MatInputModule here
  ]
})
export class ProtectedModule { }