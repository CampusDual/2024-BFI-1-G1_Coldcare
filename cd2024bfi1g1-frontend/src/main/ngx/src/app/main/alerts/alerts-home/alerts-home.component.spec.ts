import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AlertsHomeComponent } from './alerts-home.component';

describe('AlertsHomeComponent', () => {
  let component: AlertsHomeComponent;
  let fixture: ComponentFixture<AlertsHomeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AlertsHomeComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AlertsHomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
