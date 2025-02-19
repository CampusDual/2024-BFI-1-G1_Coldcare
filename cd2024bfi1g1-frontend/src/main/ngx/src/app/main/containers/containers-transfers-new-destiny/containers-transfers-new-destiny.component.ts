import { Component, ViewChild } from '@angular/core';
import { OIntegerInputComponent } from 'ontimize-web-ngx';

  @Component({
    selector: 'app-containers-transfers-new-destiny',
    templateUrl: './containers-transfers-new-destiny.component.html',
    styleUrls: ['./containers-transfers-new-destiny.component.css']
  })

export class ContainersTransfersNewDestinyComponent {

  @ViewChild('inputDestiny', { static: false }) inputDestiny!: OIntegerInputComponent;

  constructor() {}

  onRowClick(event: any): void {

    if (event.row.CL_ID) {
      this.inputDestiny.setValue(event.row.CL_ID);
    }
  }
}
