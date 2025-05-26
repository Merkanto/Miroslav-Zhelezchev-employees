import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { EmployeeCollaboration } from '../models/employee-collaboration.model';

@Injectable({
  providedIn: 'root'
})
export class EmployeeCollaborationService {
  private apiUrl = 'http://localhost:8080/api/collaborations'; // adjust if backend differs

  constructor(private http: HttpClient) {}

  uploadCsv(file: File): Observable<EmployeeCollaboration[]> {
    const formData = new FormData();
    formData.append('file', file);
    return this.http.post<EmployeeCollaboration[]>('http://localhost:8080/api/employees/upload', formData);
  }
}
