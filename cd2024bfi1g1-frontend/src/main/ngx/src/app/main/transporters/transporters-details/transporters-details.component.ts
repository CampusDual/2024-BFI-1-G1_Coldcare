import { Component, ViewChild } from '@angular/core';
import { OButtonComponent, OTextInputComponent, OFormComponent } from 'ontimize-web-ngx';
import { TRP_STATUS_END, TRP_STATUS_INIT } from 'src/app/shared/constants';


@Component({
  selector: 'app-transporters-details',
  templateUrl: './transporters-details.component.html',
  styleUrls: ['./transporters-details.component.css']
})
export class TransportersDetailsComponent {

  @ViewChild('init', { static: false }) init!: OButtonComponent;
  @ViewChild('end', { static: false }) end!: OButtonComponent;
  @ViewChild('stateInput', { static: false }) stateInput!: OTextInputComponent;
  @ViewChild('form', { static: false }) form!: OFormComponent; 

  setStatusInit() {
    this.updateTransportState(TRP_STATUS_INIT);
  }

  setStatusEnd() {
    this.updateTransportState(TRP_STATUS_END);
  }

  private updateTransportState(newState: string) {
    if (this.form) {
      this.stateInput.setValue(newState);
      this.form.setFieldValue('TRP_STATE', newState);
    }
  }
  
}
