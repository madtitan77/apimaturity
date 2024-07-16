import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProtectedRoutingModule } from './protected-routing.module';
import { ClientsListComponent } from '../clients-list/clients-list.component';
import { ClientAddComponent } from '../client-add/client-add.component';
import { ClientEditComponent } from '../client-edit/client-edit.component';
import { ClientDetailComponent } from '../client-detail/client-detail.component';
// Updated imports for Angular 16
import { MatFormFieldModule } from '@angular/material/form-field';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatTableModule } from '@angular/material/table';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';

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
    FormsModule,
    MatInputModule,
    MatTableModule,
    ReactiveFormsModule,
    MatIconModule, 
    MatMenuModule, 
  ]
  
})
export class ProtectedModule {/*...*/}