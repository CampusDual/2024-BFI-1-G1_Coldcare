import { Component, ViewChild } from '@angular/core';
import { OIntegerInputComponent, OTableComponent } from 'ontimize-web-ngx';

@Component({
  selector: 'app-containers-transfers-new-destiny',
  templateUrl: './containers-transfers-new-destiny.component.html',
  styleUrls: ['./containers-transfers-new-destiny.component.css']
})

export class ContainersTransfersNewDestinyComponent {

  @ViewChild('inputDestiny', { static: false }) inputDestiny!: OIntegerInputComponent;
  @ViewChild('tableDestiny', { static: false }) tableDestiny!: OTableComponent;

  constructor() { }

  onRowClick(event: any): void {

    const data = this.tableDestiny.getDataArray();

    data.forEach((row: any) => { row.SELECTED = null; });

    if (event.row.CL_ID) {
      this.inputDestiny.setValue(event.row.CL_ID);
      event.row.SELECTED = true;
    }
  }
}
