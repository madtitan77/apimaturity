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
import { MatTableModule } from '@angular/material/table';
import { ReactiveFormsModule } from '@angular/forms'; 



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
    MatInputModule, // Add MatInputModule here
    MatTableModule,
    ReactiveFormsModule,
  ]
  
})
export class ProtectedModule { }