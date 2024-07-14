import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProtectedRoutingModule } from './protected-routing.module';
import { ClientsListComponent } from '../clients-list/clients-list.component';
import { ClientAddComponent } from '../client-add/client-add.component';
import { ClientEditComponent } from '../client-edit/client-edit.component';
import { ClientDetailComponent } from '../client-detail/client-detail.component';

@NgModule({
  declarations: [
    ClientsListComponent,
    ClientAddComponent,
    ClientEditComponent,
    ClientDetailComponent
  ],
  imports: [
    CommonModule,
    ProtectedRoutingModule
  ]
})
export class ProtectedModule { }