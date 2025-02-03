import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ContainersBooleanRendererComponent } from './containers-boolean-renderer.component';

describe('ContainersBooleanRendererComponent', () => {
  let component: ContainersBooleanRendererComponent;
  let fixture: ComponentFixture<ContainersBooleanRendererComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ContainersBooleanRendererComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ContainersBooleanRendererComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
