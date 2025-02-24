import { Component, ViewChild } from '@angular/core';
import { OIntegerInputComponent } from 'ontimize-web-ngx';

@Component({
  selector: 'app-containers-transfers-new-origin',
  templateUrl: './containers-transfers-new-origin.component.html',
  styleUrls: ['./containers-transfers-new-origin.component.css']
})
export class ContainersTransfersNewOriginComponent {

  @ViewChild('inputOrigin', { static: false }) inputOrigin!: OIntegerInputComponent;

  constructor() {}

  onRowSelected(event: any): void {
    
    if (event[0].CL_ID) {
      this.inputOrigin.setValue(event[0].CL_ID);
    }
  }
}
