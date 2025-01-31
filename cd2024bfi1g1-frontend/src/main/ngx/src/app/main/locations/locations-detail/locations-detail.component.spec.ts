import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LocationsDetailComponent } from './locations-detail.component';

describe('LocationsDetailComponent', () => {
  let component: LocationsDetailComponent;
  let fixture: ComponentFixture<LocationsDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LocationsDetailComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LocationsDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
