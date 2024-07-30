import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AssessmentGroup } from './models/assessment-group.model';

@Injectable({
  providedIn: 'root'
})
export class AssessmentGroupService {
  private apiUrl = 'http://localhost:8080/api/apimaturity/assessment-group';

  constructor(private http: HttpClient) { }

  getAssessmentGroups(): Observable<AssessmentGroup[]> {
    return this.http.get<AssessmentGroup[]>(this.apiUrl);
  }

  getAssessmentGroupById(id: number): Observable<AssessmentGroup> {
    return this.http.get<AssessmentGroup>(`${this.apiUrl}/${id}`);
  }

  createAssessmentGroup(assessmentGroup: AssessmentGroup): Observable<AssessmentGroup> {
    return this.http.post<AssessmentGroup>(this.apiUrl, assessmentGroup);
  }

  updateAssessmentGroup(assessmentGroup: AssessmentGroup): Observable<AssessmentGroup> {
    return this.http.put<AssessmentGroup>(`${this.apiUrl}/${assessmentGroup.assessmentNumber}`, assessmentGroup);
  }

  deleteAssessmentGroup(id: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }
}