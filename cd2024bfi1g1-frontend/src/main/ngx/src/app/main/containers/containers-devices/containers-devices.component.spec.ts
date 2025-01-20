import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ContainersDevicesComponent } from './containers-devices.component';

describe('ContainersDevicesComponent', () => {
  let component: ContainersDevicesComponent;
  let fixture: ComponentFixture<ContainersDevicesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ContainersDevicesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ContainersDevicesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
