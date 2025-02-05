import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LocationsHomeComponent } from './locations-home.component';

describe('LocationsHomeComponent', () => {
  let component: LocationsHomeComponent;
  let fixture: ComponentFixture<LocationsHomeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LocationsHomeComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LocationsHomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
