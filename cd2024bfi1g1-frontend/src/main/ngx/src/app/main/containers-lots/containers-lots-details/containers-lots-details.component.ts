import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-containers-lots-details',
  templateUrl: './containers-lots-details.component.html',
  styleUrls: ['./containers-lots-details.component.css']
})
export class ContainersLotsDetailsComponent {

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
    if (row && row.CL_ID) {
      this.router.navigate(['main', 'containers-lots', row.CL_ID], { queryParams: { isdetail: true } });
    }
  }
}
