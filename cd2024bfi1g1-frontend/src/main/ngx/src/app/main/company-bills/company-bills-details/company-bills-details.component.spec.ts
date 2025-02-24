import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CompanyBillsDetailsComponent } from './company-bills-details.component';

describe('CompanyBillsDetailsComponent', () => {
  let component: CompanyBillsDetailsComponent;
  let fixture: ComponentFixture<CompanyBillsDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CompanyBillsDetailsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CompanyBillsDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
