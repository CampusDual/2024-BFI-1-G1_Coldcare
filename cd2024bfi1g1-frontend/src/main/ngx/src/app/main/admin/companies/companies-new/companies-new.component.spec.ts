import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CompaniesNewComponent } from './companies-new.component';

describe('CompaniesNewComponent', () => {
  let component: CompaniesNewComponent;
  let fixture: ComponentFixture<CompaniesNewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CompaniesNewComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CompaniesNewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
