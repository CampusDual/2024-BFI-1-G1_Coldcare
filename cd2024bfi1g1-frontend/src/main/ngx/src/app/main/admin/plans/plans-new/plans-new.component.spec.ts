import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PlansNewComponent } from './plans-new.component';

describe('PlansNewComponent', () => {
  let component: PlansNewComponent;
  let fixture: ComponentFixture<PlansNewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PlansNewComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PlansNewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
