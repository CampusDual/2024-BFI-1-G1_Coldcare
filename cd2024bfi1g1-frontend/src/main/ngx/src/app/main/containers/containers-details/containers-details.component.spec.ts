import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ContainersDetailsComponent } from './containers-details.component';

describe('ContainersDetailsComponent', () => {
  let component: ContainersDetailsComponent;
  let fixture: ComponentFixture<ContainersDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ContainersDetailsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ContainersDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
