import { Component, ViewChild } from '@angular/core';
import { OTableComponent, OTextInputComponent } from 'ontimize-web-ngx';

@Component({
  selector: 'app-transports-home',
  templateUrl: './transports-home.component.html',
  styleUrls: ['./transports-home.component.css']
})
export class TransportsHomeComponent {

  @ViewChild('transporterTable', { static: false }) transporterTable!: OTextInputComponent;
  @ViewChild('transportsTable', { static: false }) transportsTable!: OTableComponent;

  fillData() {
    let array = this.transportsTable.getDataArray();
    for (let i = 0; i < array.length; i++) {
      if (array[i].USR_NAME === undefined)
        array[i].USR_NAME = "N/A";
    }
  }
}
