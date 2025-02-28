import { Component, ViewChild } from '@angular/core';
import { OComboComponent, ORealInputComponent } from 'ontimize-web-ngx';
import { Router } from '@angular/router';

@Component({
  selector: 'app-lots-details',
  templateUrl: './lots-details.component.html',
  styleUrls: ['./lots-details.component.css']
})
export class LotsDetailsComponent {

  constructor(private router: Router) { }

  @ViewChild('productCombo', { static: false }) productCombo!: OComboComponent;
  @ViewChild('minTempInput', { static: false }) minTempInput!: ORealInputComponent;
  @ViewChild('maxTempInput', { static: false }) maxTempInput!: ORealInputComponent;

  graphData: any[] = [];

  updateGraph(event: any) {
    console.log('Datos del grafo:', event);
    this.graphData = event || [];
  }

  importTemperature() {
    const data = this.productCombo.getDataArray();
    for (let row of data) {
      if (this.productCombo.getValue() === row.PRO_ID) {
        this.minTempInput.setValue(row.PRO_MIN_TEMP);
        this.maxTempInput.setValue(row.PRO_MAX_TEMP);
      }
    }
  }

  public openContainersLots(selected: any) {
    const row = selected.row;
    if (row && row.CL_ID) {
      this.router.navigate(['main', 'containers-lots', row.CL_ID], { queryParams: { isdetail: true } });
    }
  }
}