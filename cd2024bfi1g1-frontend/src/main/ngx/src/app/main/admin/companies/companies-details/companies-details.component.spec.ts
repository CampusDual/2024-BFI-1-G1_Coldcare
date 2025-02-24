import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CompaniesDetailsComponent } from './companies-details.component';

describe('CompaniesDetailsComponent', () => {
  let component: CompaniesDetailsComponent;
  let fixture: ComponentFixture<CompaniesDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CompaniesDetailsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CompaniesDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
