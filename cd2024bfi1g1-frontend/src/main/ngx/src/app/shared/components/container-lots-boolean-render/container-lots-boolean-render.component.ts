import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-container-lots-boolean-render',
  templateUrl: './container-lots-boolean-render.component.html',
  styleUrls: ['./container-lots-boolean-render.component.css']
})
export class ContainerLotsBooleanRenderComponent {
  @Input() cntMobility: boolean = false;
}
