import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AlertsIsCurrentRenderComponent } from './alerts-is-current-render.component';

describe('AlertsIsCurrentRenderComponent', () => {
  let component: AlertsIsCurrentRenderComponent;
  let fixture: ComponentFixture<AlertsIsCurrentRenderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AlertsIsCurrentRenderComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AlertsIsCurrentRenderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
