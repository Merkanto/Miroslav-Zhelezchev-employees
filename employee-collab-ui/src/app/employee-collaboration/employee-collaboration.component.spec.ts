import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeeCollaborationComponent } from './employee-collaboration.component';

describe('EmployeeCollaborationComponent', () => {
  let component: EmployeeCollaborationComponent;
  let fixture: ComponentFixture<EmployeeCollaborationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EmployeeCollaborationComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EmployeeCollaborationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
