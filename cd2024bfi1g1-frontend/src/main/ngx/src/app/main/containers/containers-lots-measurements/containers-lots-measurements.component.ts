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
    this.redirect(selected.row);
  }

  private redirect(selected: any) {
    if (selected && selected.CNT_ID && selected.CL_ID) {
      this.router.navigate(['main', 'containers', selected.CNT_ID, selected.CL_ID], { queryParams: { isdetail: true } });
    }
  }

}
