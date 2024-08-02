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
    const clientId = +(this.route.snapshot.paramMap.get('id') ?? 0);
    this.assessmentGroupService.createAssessmentGroup(clientId,this.assessmentGroup).subscribe({
      next: (assessmentGroup) => {
        console.log('Assessment Group added', assessmentGroup);
        this.assessmentGroup = new AssessmentGroup(); 
        this.router.navigate(['/assessment-groups/']);
      },
      error: (err) => {
        this.handleError(err,'assessment');
      }
    });
  }


  
  getClient(): void {
    const clientId = +(this.route.snapshot.paramMap.get('id') ?? 0);
    if (clientId) {
      this.clientsService.getClientById(clientId).subscribe({
        next: (client) => {
          if (client) {
            this.clientName = client.name;
            //this.assessmentGroup.clientId = client.clientId;
            this.errorOccurred = false; // Reset error flag on successful response
          } else {
            console.error('Client not found');
            this.errorOccurred = true;
            this.errorMessage = 'Client not found'; // Set a generic error message
          }
        },
        error: (err) => {
          this.handleError(err, 'client');
        }
      });
    }
  }



  handleError(err: any, message: string): void {
    console.error('An error occurred:', err.message);
    if (err.status === 403) {
      this.errorOccurred = true;
      this.errorMessage = `Access denied. You do not have permission to view this ${message}.`;
    } else if (err.status === 404) {
      this.errorOccurred = true;
      this.errorMessage = `${message} not found`;
    } else if (err.status === 409) {
      this.errorOccurred = true;
      this.errorMessage = `${message} already exists`;
    } else {
      this.errorOccurred = true;
      this.errorMessage = 'An unexpected error occurred.';
    }
  }

}