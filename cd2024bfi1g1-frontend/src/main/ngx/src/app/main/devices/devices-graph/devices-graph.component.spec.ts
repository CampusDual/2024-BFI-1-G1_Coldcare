import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DevicesGraphComponent } from './devices-graph.component';

describe('DevicesGraphComponent', () => {
  let component: DevicesGraphComponent;
  let fixture: ComponentFixture<DevicesGraphComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DevicesGraphComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DevicesGraphComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
