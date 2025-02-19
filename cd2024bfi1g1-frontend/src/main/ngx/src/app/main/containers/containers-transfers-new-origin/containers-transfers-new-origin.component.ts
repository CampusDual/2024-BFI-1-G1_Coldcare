import { Component, ViewChild } from '@angular/core';
import { OIntegerInputComponent, OTableComponent } from 'ontimize-web-ngx';

@Component({
  selector: 'app-containers-transfers-new-origin',
  templateUrl: './containers-transfers-new-origin.component.html',
  styleUrls: ['./containers-transfers-new-origin.component.css']
})
export class ContainersTransfersNewOriginComponent {

  @ViewChild('inputOrigin', { static: false }) inputOrigin!: OIntegerInputComponent;
  @ViewChild('tableOrigin', { static: false }) tableOrigin!: OTableComponent;

  constructor() {}

  onRowClick(event: any): void {

    const data = this.tableOrigin.getDataArray();

    data.forEach((row: any) => { row.SELECTED = null });
    
    if (event.row.CL_ID) {
      this.inputOrigin.setValue(event.row.CL_ID);
      event.row.SELECTED = true;
    }
  }
}
