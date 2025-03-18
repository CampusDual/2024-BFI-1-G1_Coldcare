import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PlansHomeComponent } from './plans-home.component';

describe('PlansHomeComponent', () => {
  let component: PlansHomeComponent;
  let fixture: ComponentFixture<PlansHomeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PlansHomeComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PlansHomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
