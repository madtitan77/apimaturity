import { Component, OnInit } from '@angular/core';
import { Client } from '../models/clients.model'; 
import { ClientsService } from '../clients.service'; 
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router'; 

@Component({
  selector: 'app-client-add',
  templateUrl: './client-add.component.html',
  styleUrls: ['./client-add.component.css']
})
export class ClientAddComponent implements OnInit {
  client: Client = new Client(); // Initialize a new Client object

  constructor(
      private clientsService: ClientsService,
      private titleService: Title,
      private router : Router
  ) { }

  ngOnInit(): void {
    this.titleService.setTitle('New Client');
  }

  addClient(): void {
    this.clientsService.createClient(this.client).subscribe({
      next: (client) => {
        console.log('Client added', client);
        this.client = new Client(); // Reset the form
        this.router.navigate(['/clients/']);
      },
      error: (error) => console.error('There was an error!', error)
    });
  }
}