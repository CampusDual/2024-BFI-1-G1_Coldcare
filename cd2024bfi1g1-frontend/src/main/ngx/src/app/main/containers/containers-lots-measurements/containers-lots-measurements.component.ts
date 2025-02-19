import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-containers-lots-measurements',
  templateUrl: './containers-lots-measurements.component.html',
  styleUrls: ['./containers-lots-measurements.component.css']
})
export class ContainersLotsMeasurementsComponent {

  constructor(
    private router: Router
  ) { }

  public rowClass = (rowData: any, rowIndex: number): string | string[] => {

    const temp = rowData.ME_TEMP;
    const minTemp = rowData.MIN_TEMP;
    const maxTemp = rowData.MAX_TEMP;

    if (temp < minTemp || temp > maxTemp) {
      return "error-row";
    }
    return '';
  }

  public openContainersLotsDetail(selected: any) {
    const row = selected.row;
    if (row && row.CNT_ID && row.CL_ID) {
      this.router.navigate(['main', 'containers', row.CNT_ID, row.CL_ID], { queryParams: { isdetail: true } });
    }
  }
}
