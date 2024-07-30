import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AssessmentGroupAddComponent } from './assessment-group-add.component';

describe('AssessmentGroupAddComponent', () => {
  let component: AssessmentGroupAddComponent;
  let fixture: ComponentFixture<AssessmentGroupAddComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AssessmentGroupAddComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AssessmentGroupAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
