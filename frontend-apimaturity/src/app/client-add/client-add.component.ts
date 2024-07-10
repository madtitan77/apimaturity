import { Component, OnInit } from '@angular/core';
import { Client } from '../models/clients.model'; 
import { ClientsService } from '../clients.service'; 
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-client-add',
  templateUrl: './client-add.component.html',
  styleUrls: ['./client-add.component.css']
})
export class ClientAddComponent implements OnInit {
  client: Client = new Client(); // Initialize a new Client object

  constructor(
      private clientsService: ClientsService,
      private titleService: Title
  ) { }

  ngOnInit(): void {
    this.titleService.setTitle('New Client');
  }

  addClient(): void {
    this.clientsService.createClient(this.client).subscribe({
      next: (client) => {
        console.log('Client added', client);
        this.client = new Client(); // Reset the form
      },
      error: (error) => console.error('There was an error!', error)
    });
  }
}