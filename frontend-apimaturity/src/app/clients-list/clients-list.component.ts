import { Component, OnInit } from '@angular/core';
import { ClientsService } from '../clients.service'; // Adjust the path as necessary
import { Client } from '../models/clients.model'; // Adjust the path as necessary

@Component({
  selector: 'app-clients-list',
  templateUrl: './clients-list.component.html',
  styleUrls: ['./clients-list.component.css']
})
export class ClientsListComponent implements OnInit {
  clients: Client[] = []; // Array to hold clients data

  constructor(private clientsService: ClientsService) { }

  ngOnInit(): void {
    this.fetchClients();
  }

  fetchClients(): void {
    this.clientsService.getClients().subscribe(
      (data: Client[]) => {
        this.clients = data;
      },
      error => {
        console.error('There was an error!', error);
      }
    );
  }

  // Implement other CRUD operations as methods here, similar to fetchClients
}