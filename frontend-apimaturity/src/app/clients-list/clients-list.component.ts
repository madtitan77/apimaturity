import { Component, OnInit } from '@angular/core';
import { ClientsService } from '../clients.service'; 
import { Client } from '../models/clients.model'; 
import { Router } from '@angular/router'; 
import { FormGroup, FormBuilder } from '@angular/forms';


@Component({
  selector: 'app-clients-list',
  templateUrl: './clients-list.component.html',
  styleUrls: ['./clients-list.component.css']
})
export class ClientsListComponent implements OnInit {
  clients: Client[] = []; 
  selectedClient: Client | null = null;
  editForm!: FormGroup;
  displayedColumns: string[] = ['name','notes','industry'];

  constructor(
    private clientsService: ClientsService, private router: Router,
    private fb: FormBuilder

  ) { }

  ngOnInit(): void {
    this.fetchClients();
    this.editForm = this.fb.group({
      name: [''],
      notes: [''],
      industry: [''],
      // Initialize other form controls
    });
  }

  selectClient(client: Client) {
    this.selectedClient = client;
    this.editForm.patchValue(client);
  }

  save() {
    // Implement client update logic here
    this.selectedClient = null; // Deselect client after save
  }

  fetchClients(): void {
    this.clientsService.getClients().subscribe(
      (data: Client[]) => {
        this.clients = data;
        if (this.clients.length === 0) {
          // If no clients, navigate to client-add
          this.router.navigate(['/clients/add']); 
        }
      },
      error => {
        console.error('There was an error!', error);
      }
    );
  }

  // Implement other CRUD operations as methods here, similar to fetchClients
}