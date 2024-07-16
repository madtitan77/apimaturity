import { Component, OnInit, ViewChild } from '@angular/core';
import { ClientsService } from '../clients.service'; 
import { Client } from '../models/clients.model'; 
import { Router } from '@angular/router'; 
import { FormGroup, FormBuilder } from '@angular/forms';
import { MatMenuTrigger } from '@angular/material/menu';



@Component({
  selector: 'app-clients-list',
  templateUrl: './clients-list.component.html',
  styleUrls: ['./clients-list.component.css']
})
export class ClientsListComponent implements OnInit {
  clients: Client[] = []; 
  selectedClient: Client | null = null;
  editForm!: FormGroup;
  displayedColumns: string[] = ['name','notes','industry', 'actions'];
  currentRow: any; 

  @ViewChild(MatMenuTrigger,{ static: false }) contextMenuTrigger!: MatMenuTrigger;


  constructor(
    private clientsService: ClientsService, 
    private router: Router,
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
    console.log('Editing client', client);
    this.selectClient(client); // Assuming selectClient sets up for editing
    // Additional logic for editing
    this.router.navigate(['/clients/add']); 

  }
  
  openContextMenu(event: MouseEvent, row: any): void {
    event.preventDefault();
    this.currentRow = row;
    if (this.contextMenuTrigger) {
      this.contextMenuTrigger.openMenu();
    } else {
      console.error('ContextMenuTrigger is not available.');
    }
  }

  deleteClient(client: Client) {
    // Implement deletion logic here
    console.log('Deleting client', client);
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