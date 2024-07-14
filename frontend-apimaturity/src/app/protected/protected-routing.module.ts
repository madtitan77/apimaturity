import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ClientsListComponent } from '../clients-list/clients-list.component';
import { ClientAddComponent } from '../client-add/client-add.component';
import { ClientEditComponent } from '../client-edit/client-edit.component';
import { ClientDetailComponent } from '../client-detail/client-detail.component';

const routes: Routes = [
  { path: 'clients', component: ClientsListComponent },
  { path: 'clients/add', component: ClientAddComponent },
  { path: 'clients/edit/:id', component: ClientEditComponent },
  { path: 'clients/:id', component: ClientDetailComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProtectedRoutingModule { }