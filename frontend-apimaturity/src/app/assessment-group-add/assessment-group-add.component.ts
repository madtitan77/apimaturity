import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { AssessmentGroup } from '../models/assessment-group.model';
import { AssessmentGroupService } from '../assessmentGroup.service';
import { ClientsService } from '../clients.service';



@Component({
  selector: 'app-assessment-group-add',
  templateUrl: './assessment-group-add.component.html',
  styleUrls: ['./assessment-group-add.component.css'] // Assuming you want to reuse the same styles
})
export class AssessmentGroupAddComponent implements OnInit {
  assessmentGroup: AssessmentGroup = new AssessmentGroup();
  clientName: string = '';
  errorOccurred: boolean = false; // Flag to indicate an error
  errorMessage: string = ''; // Property to hold the error message


  constructor(
    private assessmentGroupService: AssessmentGroupService,
    private clientsService: ClientsService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.getClient();
  }

  addAssessmentGroup(): void {
    this.assessmentGroupService.createAssessmentGroup(this.assessmentGroup).subscribe({
      next: (assessmentGroup) => {
        console.log('Assessment Group added', assessmentGroup);
        this.assessmentGroup = new AssessmentGroup(); 
        this.router.navigate(['/assessment-groups/']);
      },
      error: (error) => console.error('There was an error!', error)
    });
  }


  
  getClient(): void {
    const clientId = +(this.route.snapshot.paramMap.get('id') ?? 0);
    if (clientId) {
      this.clientsService.getClientById(clientId).subscribe({
        next: (client) => {
          if (client) {
            this.clientName = client.name;
            this.errorOccurred = false; // Reset error flag on successful response
          } else {
            console.error('Client not found');
            this.errorOccurred = true;
            this.errorMessage = 'Client not found'; // Set a generic error message
          }
        },
        error: (err) => {
          console.error('An error occurred:', err.message);
          if (err.status === 403) {
            this.errorOccurred = true;
            this.errorMessage = 'Access denied. You do not have permission to view this client.';
          } else if (err.status === 404) {
            this.errorOccurred = true;
            this.errorMessage = 'Client not found';
          } else {
            this.errorOccurred = true;
            this.errorMessage = 'An unexpected error occurred.';
          }
        }
      });
    }
  }
}