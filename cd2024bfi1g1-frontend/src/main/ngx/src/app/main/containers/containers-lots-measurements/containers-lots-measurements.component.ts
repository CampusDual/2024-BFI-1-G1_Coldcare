import { Component } from '@angular/core';

@Component({
  selector: 'app-containers-lots-measurements',
  templateUrl: './containers-lots-measurements.component.html',
  styleUrls: ['./containers-lots-measurements.component.css']
})
export class ContainersLotsMeasurementsComponent {

  getRowClass(rowData: any): string {
    const temp = rowData.ME_TEMP;
    const minTemp = rowData.MIN_TEMP;
    const maxTemp = rowData.MAX_TEMP;

    if (temp < minTemp || temp > maxTemp) {
      return 'row-red'; 
    }
    return ''; 
  }

}
