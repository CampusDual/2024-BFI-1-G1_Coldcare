import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BillsMonthRendererComponent } from './bills-month-renderer.component';

describe('BillsMonthRendererComponent', () => {
  let component: BillsMonthRendererComponent;
  let fixture: ComponentFixture<BillsMonthRendererComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BillsMonthRendererComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BillsMonthRendererComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
