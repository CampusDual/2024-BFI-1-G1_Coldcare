import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ContainersHomeComponent } from './containers-home.component';

describe('ContainersHomeComponent', () => {
  let component: ContainersHomeComponent;
  let fixture: ComponentFixture<ContainersHomeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ContainersHomeComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ContainersHomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
