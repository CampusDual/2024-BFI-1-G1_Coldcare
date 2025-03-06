import { Component, ViewChild } from '@angular/core';
import { OTextInputComponent } from 'ontimize-web-ngx';

@Component({
  selector: 'app-transports-details',
  templateUrl: './transports-details.component.html',
  styleUrls: ['./transports-details.component.css']
})
export class TransportsDetailsComponent {

  @ViewChild('transporter', { static: false }) transporter!: OTextInputComponent;

  fillData(e: any) {
    if (e.USR_NAME === undefined) {
      this.transporter.setValue("N/A");
    }
  }
}
