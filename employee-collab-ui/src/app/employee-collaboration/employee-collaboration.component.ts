import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EmployeeCollaborationService } from '../services/employee-collaboration.service';
import { EmployeeCollaboration } from '../models/employee-collaboration.model';

// Angular Material modules
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatCardModule } from '@angular/material/card';
import { MatError } from '@angular/material/form-field';

@Component({
  selector: 'app-employee-collaboration',
  standalone: true,
  imports: [
    CommonModule,
    MatTableModule,
    MatButtonModule,
    MatInputModule,
    MatCardModule
  ],
  templateUrl: './employee-collaboration.component.html',
  styleUrls: ['./employee-collaboration.component.css']
})
export class EmployeeCollaborationComponent {
  selectedFile: File | null = null;
  collaborations: EmployeeCollaboration[] = [];
  errorMessage: string = '';
  displayedColumns: string[] = ['employee1Id', 'employee2Id', 'projectId', 'daysWorkedTogether'];

  constructor(private collaborationService: EmployeeCollaborationService) {}

  onFileSelected(event: Event): void {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      this.selectedFile = input.files[0];
      this.errorMessage = '';
    }
  }

  onUpload(): void {
    if (!this.selectedFile) {
      this.errorMessage = 'Please select a CSV file to upload.';
      return;
    }

    this.collaborationService.uploadCsv(this.selectedFile).subscribe({
      next: (data) => {
        this.collaborations = data;
        this.errorMessage = '';
      },
      error: (error) => {
        console.error('Upload failed:', error);
        this.errorMessage = 'Failed to upload file. Please try again.';
        this.collaborations = [];
      }
    });
  }
}
