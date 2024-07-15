import { Component, OnInit } from '@angular/core';
import { ClientsService } from '../clients.service'; 
import { Client } from '../models/clients.model'; 
import { Router } from '@angular/router'; 
import { FormGroup, FormBuilder } from '@angular/forms';
import { MatLegacyMenuTrigger as MatMenuTrigger } from '@angular/material/legacy-menu';


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
  currentRow: any; 

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

  askToNavigate(client: Client): void {
    const userConfirmed = window.confirm(`Do you want to look into the assessments of ${client.name} with id ${client.clientId}?`);
    if (userConfirmed) {
      // Navigate to the assessments page for the selected client
      this.router.navigate([`/clients/${client.clientId}/assessments`]);
    }
  }

  navigateToAddClient(): void {
    this.router.navigate(['/clients/add']);
  }

  editClient(client: Client) {
    this.selectClient(client); // Assuming selectClient sets up for editing
    // Additional logic for editing
  }
  
  openContextMenu(event: MouseEvent, contextMenu: MatMenuTrigger, row: any) {
    event.preventDefault(); // Prevent the browser context menu
    this.currentRow  = row; // Pass the row as data to the menu
    contextMenu.openMenu(); // Open the menu
  }

  deleteClient(client: Client) {
    // Implement deletion logic here
    this.clientsService.deleteClient(client.clientId).subscribe({
      next: () => {
        console.log('Client deleted', client);
        this.fetchClients();
      },
      error: (error) => console.error('There was an error!', error)
    });
  }



  save() {
    // Implement client update logic here
    this.selectedClient = null; // Deselect client after save
  }

  fetchClients(): void {
    this.clientsService.getClients().subscribe(
      (data: Client[]) => {
        this.clients = data;
        console.log('data fetched: ', data)
        this.clients.forEach(client => {
          console.log(`Client ID: ${client.clientId}, Client Name: ${client.name}`);
        });
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