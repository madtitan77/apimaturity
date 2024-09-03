import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AssessmentGroup } from './models/assessment-group.model';

@Injectable({
  providedIn: 'root'
})
export class AssessmentGroupService {
  private baseUrl = 'http://localhost:8080/api/apimaturity/clients';

  constructor(private http: HttpClient) { }

  getAssessmentGroups(clientId: number): Observable<AssessmentGroup[]> {
    const url = `${this.baseUrl}/${clientId}/assessment-groups/`;
    return this.http.get<AssessmentGroup[]>(url);
  }

  getAssessmentGroupById(clientId: number, id: number): Observable<AssessmentGroup> {
    const url = `${this.baseUrl}/${clientId}/assessment-groups/${id}`;
    return this.http.get<AssessmentGroup>(url);
  }

  createAssessmentGroup(clientId: number, assessmentGroup: AssessmentGroup): Observable<AssessmentGroup> {
    const url = `${this.baseUrl}/${clientId}/assessment-groups/`;
    assessmentGroup.setClientId(clientId);
    
    // Destructure the assessmentGroup to exclude assessmentNumber and use the rest for the new object
    const { assessmentNumber, ...assessmentGroupWithoutNumber } = assessmentGroup;
    
    return this.http.post<AssessmentGroup>(url, assessmentGroupWithoutNumber);
  }

  updateAssessmentGroup(clientId: number, assessmentGroup: AssessmentGroup): Observable<AssessmentGroup> {
    const url = `${this.baseUrl}/${clientId}/assessment-groups/${assessmentGroup.assessmentNumber}`;
    return this.http.put<AssessmentGroup>(url, assessmentGroup);
  }

  deleteAssessmentGroup(clientId: number, id: number): Observable<any> {
    const url = `${this.baseUrl}/${clientId}/assessment-groups/${id}`;
    return this.http.delete(url);
  }
}