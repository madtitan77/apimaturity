import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProtectedRoutingModule } from './protected-routing.module';
import { ClientsListComponent } from '../clients-list/clients-list.component';
import { ClientAddComponent } from '../client-add/client-add.component';
import { ClientEditComponent } from '../client-edit/client-edit.component';
import { ClientDetailComponent } from '../client-detail/client-detail.component';
import { MatLegacyFormFieldModule as MatFormFieldModule } from '@angular/material/legacy-form-field';
import { FormsModule } from '@angular/forms';
import { MatLegacyInputModule as MatInputModule } from '@angular/material/legacy-input'; 
import { MatLegacyTableModule as MatTableModule } from '@angular/material/legacy-table';
import { ReactiveFormsModule } from '@angular/forms'; 
import { MatIconModule } from '@angular/material/icon';
import { MatLegacyMenuModule as MatMenuModule } from '@angular/material/legacy-menu';




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
    MatIconModule, 
    MatMenuModule, 
  ]
  
})
export class ProtectedModule { }