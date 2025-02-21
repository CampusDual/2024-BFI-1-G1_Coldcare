import { Component, ViewChild } from '@angular/core';
import { OComboComponent, ORealInputComponent } from 'ontimize-web-ngx';

@Component({
  selector: 'app-lots-new',
  templateUrl: './lots-new.component.html',
  styleUrls: ['./lots-new.component.css']
})
export class LotsNewComponent {

  @ViewChild('productCombo', {static: false} ) productCombo!: OComboComponent;
  @ViewChild('minTempInput', { static: false }) minTempInput!: ORealInputComponent;
  @ViewChild('maxTempInput', { static: false }) maxTempInput!: ORealInputComponent;

  onNewProductChange(event: any) {
    if (!event || !event.newValue) return;
    let selectedProduct = event.newValue;
    const data = this.productCombo.getDataArray();
    for (let row of data) {
       if (selectedProduct === row.PRO_ID) {
        this.minTempInput.setValue(row.PRO_MIN_TEMP);
        this.maxTempInput.setValue(row.PRO_MAX_TEMP);
       }
    }
  }
}
