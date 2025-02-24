import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PlansPricesNewComponent } from './plans-prices-new.component';

describe('PlansPricesNewComponent', () => {
  let component: PlansPricesNewComponent;
  let fixture: ComponentFixture<PlansPricesNewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PlansPricesNewComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PlansPricesNewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
