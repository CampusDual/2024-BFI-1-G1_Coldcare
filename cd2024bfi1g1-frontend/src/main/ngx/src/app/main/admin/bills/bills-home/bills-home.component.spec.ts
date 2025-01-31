import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BillsHomeComponent } from './bills-home.component';

describe('BillsHomeComponent', () => {
  let component: BillsHomeComponent;
  let fixture: ComponentFixture<BillsHomeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [BillsHomeComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(BillsHomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
