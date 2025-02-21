import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ContainersHomeBooleanRenderComponent } from './containers-home-boolean-render.component';

describe('ContainersHomeBooleanRenderComponent', () => {
  let component: ContainersHomeBooleanRenderComponent;
  let fixture: ComponentFixture<ContainersHomeBooleanRenderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ContainersHomeBooleanRenderComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ContainersHomeBooleanRenderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
