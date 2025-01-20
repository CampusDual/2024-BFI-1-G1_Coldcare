import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ContainersEditComponent } from './containers-edit.component';

describe('ContainersEditComponent', () => {
  let component: ContainersEditComponent;
  let fixture: ComponentFixture<ContainersEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ContainersEditComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ContainersEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
