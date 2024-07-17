import { Component, OnInit, ViewChild } from '@angular/core';
import { ClientsService } from '../clients.service'; 
import { Client } from '../models/clients.model'; 
import { Router } from '@angular/router'; 
import { FormGroup, FormBuilder, FormArray} from '@angular/forms';
import { MatMenuTrigger } from '@angular/material/menu';




@Component({
  selector: 'app-clients-list',
  templateUrl: './clients-list.component.html',
  styleUrls: ['./clients-list.component.css']
})
export class ClientsListComponent implements OnInit {
  Clients: Client[] = []; 
  selectedClient: Client | null = null;
  editForm!: FormGroup;
  //displayedColumns: string[] = ['name','notes','industry','actions'];
  displayedColumns: string[] = ['name','notes','industry','actions'];
  currentRow: any; 
  clientsForm!: FormGroup;

  @ViewChild(MatMenuTrigger,{ static: false }) contextMenuTrigger!: MatMenuTrigger;


  constructor(
    private clientsService: ClientsService, 
    private router: Router,
    private fb: FormBuilder
  ) { 
      this.clientsForm = this.fb.group({
      clientsFormArray: this.fb.array([])
    });
  }

  ngOnInit(): void {
    this.fetchClients();
    this.editForm = this.fb.group({
      name: [''],
      notes: [''],
      industry: [''],
      // Initialize other form controls
    });
    this.loadClients()
  }

  selectClient(client: Client) {
    this.selectedClient = client;
    this.editForm.patchValue(client);
  }

  loadClients() {
    console.log(`Loading clients with array ${this.Clients}`);
    const clientFormGroups = this.Clients.map(client => this.fb.group({
      clientId: [client.clientId],
      name: [client.name],
      notes: [client.notes],
      industry: [client.industry],
      user_id: [client.user_id], // Assuming you want to include user_id in the form
      isEditMode: [false] // Track edit mode for each client
    }));
    //debug the form groups
     (clientFormGroups).forEach(clientFormGroup => {
          console.log(`Client form group value name: ${clientFormGroup.value.name}`);
          console.log(`Client form group value isEditMode: ${clientFormGroup.value.isEditMode}`);
        });
    
    
    
      // Set the 'clientsFormArray' form array control with the mapped form groups
      this.clientsForm.setControl('clientsFormArray', this.fb.array(clientFormGroups));
      //print the form array
      console.log(`Form array value: ${this.clientsForm.value}`);

    

      
     
  }

  get clientsFormArray(): FormArray {
    return this.clientsForm.get('clientsFormArray') as FormArray;
  }


  toggleEditMode(index: number) {
    const client = this.clientsFormArray.at(index);
    const isEditModeControl = client.get('isEditMode');
    if (isEditModeControl) {
      isEditModeControl.setValue(!isEditModeControl.value);
    }
  }

  saveClient(index: number) {
    const client = this.clientsFormArray.at(index).value;
    // Implement save logic here, possibly calling a service
    this.toggleEditMode(index); 
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
    this.router.navigate(['/clients', client.clientId, 'edit']);
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
        this.Clients = data; 
        console.log('data fetched: ', data);
        this.Clients.forEach(client => {
          console.log(`Client ID: ${client.clientId}, Client Name: ${client.name}`);
        });
        console.log(`Clients array lengths: ${this.Clients.length}`);
        if (this.Clients.length === 0) {
          this.router.navigate(['/clients/add']);
        } else {
          console.log(`Populating form with array : ${this.Clients}`);
          this.loadClients(); 
        }
      },
      error => {
        console.error('There was an error!', error);
      }
    );
  }
  

  // Implement other CRUD operations as methods here, similar to fetchClients
}