import { Component, OnInit } from '@angular/core';
import { ClientsService } from '../clients.service'; 
import {  FormGroup, FormBuilder} from '@angular/forms';
import { ActivatedRoute } from '@angular/router';

  @Component({
    selector: 'app-client-edit',
    templateUrl: './client-edit.component.html',
    styleUrls: ['./client-edit.component.css']
  })
  export class ClientEditComponent implements OnInit {
    editForm!: FormGroup;
    clientId!: number

    constructor(
      private formBuilder: FormBuilder,
      private router: ActivatedRoute,
      private clientsService: ClientsService
      ) 
      { }

    ngOnInit(): void {
      this.clientId = this.router.snapshot.params['id']; 
      this.fetchClient();
      this.editForm = this.formBuilder.group({
        clientId: [this.clientId], 
        name: [''],
        notes: [''],
        industry: ['']
      });
      }
      
    save() {
      if (this.editForm.valid) {
        this.clientsService.updateClient(this.editForm.value).subscribe(() => {
          // TODO Handle success, e.g., redirect or show a message
        });
      }
    }

    fetchClient() {
      const clientId = this.router.snapshot.params['id'];
      console.log(`fetching client  ${clientId}`);
      this.clientsService.getClientById(clientId).subscribe(client => {
        console.log(`client for id ${clientId} returned ${client.name}`);
        this.editForm.patchValue(client);
      });
    }

  }
