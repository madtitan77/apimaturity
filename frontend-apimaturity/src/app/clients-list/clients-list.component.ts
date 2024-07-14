import { Component, OnInit } from '@angular/core';
import { ClientsService } from '../clients.service'; 
import { Client } from '../models/clients.model'; 
import { Router } from '@angular/router'; 

@Component({
  selector: 'app-clients-list',
  templateUrl: './clients-list.component.html',
  styleUrls: ['./clients-list.component.css']
})
export class ClientsListComponent implements OnInit {
  clients: Client[] = []; // Array to hold clients data

  constructor(private clientsService: ClientsService, private router: Router) { }

  ngOnInit(): void {
    this.fetchClients();
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