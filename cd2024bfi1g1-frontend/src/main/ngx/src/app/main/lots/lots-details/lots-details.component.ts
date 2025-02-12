import { Component, ViewChild } from '@angular/core';
import { OComboComponent, ORealInputComponent } from 'ontimize-web-ngx';

@Component({
  selector: 'app-lots-details',
  templateUrl: './lots-details.component.html',
  styleUrls: ['./lots-details.component.css']
})
export class LotsDetailsComponent {

  @ViewChild('productCombo', {static: false} ) productCombo!: OComboComponent;
  @ViewChild('minTempInput', { static: false }) minTempInput!: ORealInputComponent;
  @ViewChild('maxTempInput', { static: false }) maxTempInput!: ORealInputComponent;

  importTemperature() {
    const data = this.productCombo.getDataArray();
    for (let row of data) {
       if (this.productCombo.getValue() === row.PRO_ID) {
        this.minTempInput.setValue(row.PRO_MIN_TEMP);
        this.maxTempInput.setValue(row.PRO_MAX_TEMP);
       }
    }
  }
}