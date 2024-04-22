import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TasksProjectComponent } from './tasksproject.component';

describe('TasksComponent', () => {
  let component: TasksProjectComponent;
  let fixture: ComponentFixture<TasksProjectComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TasksProjectComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(TasksProjectComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
