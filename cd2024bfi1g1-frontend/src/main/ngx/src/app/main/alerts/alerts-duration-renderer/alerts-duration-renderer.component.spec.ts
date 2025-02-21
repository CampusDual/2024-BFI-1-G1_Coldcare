import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AlertsDurationRendererComponent } from './alerts-duration-renderer.component';

describe('AlertsDurationRendererComponent', () => {
  let component: AlertsDurationRendererComponent;
  let fixture: ComponentFixture<AlertsDurationRendererComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AlertsDurationRendererComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AlertsDurationRendererComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
