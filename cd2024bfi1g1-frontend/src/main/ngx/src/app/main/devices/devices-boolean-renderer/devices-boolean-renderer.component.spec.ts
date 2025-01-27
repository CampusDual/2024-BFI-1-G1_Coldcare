import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DevicesBooleanRendererComponent } from './devices-boolean-renderer.component';

describe('DevicesBooleanRendererComponent', () => {
  let component: DevicesBooleanRendererComponent;
  let fixture: ComponentFixture<DevicesBooleanRendererComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DevicesBooleanRendererComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DevicesBooleanRendererComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
