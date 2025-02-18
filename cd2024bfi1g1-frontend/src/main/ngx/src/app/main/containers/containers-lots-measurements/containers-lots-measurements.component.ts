import { Component, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { OTableComponent } from 'ontimize-web-ngx';

@Component({
  selector: 'app-containers-lots-measurements',
  templateUrl: './containers-lots-measurements.component.html',
  styleUrls: ['./containers-lots-measurements.component.css']
})
export class ContainersLotsMeasurementsComponent {

  @ViewChild('containerLotOriginTable', { static: false }) clOriginTable: OTableComponent;
  @ViewChild('containerLotDestinyTable', { static: false }) clDestinyTable: OTableComponent;

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

  public openContainerLotOriginDetail() {
    let selected = this.clOriginTable.getSelectedItems();
    this.redirect(selected);
  }

  public openContainerLotDestinyDetail() {
    let selected = this.clDestinyTable.getSelectedItems();
    this.redirect(selected);
  }

  private redirect(selected: any) {
    if (selected.length === 1) {
      let containerId = selected[0]['CNT_ID'];
      let clId = selected[0]['CL_ID'];
      this.router.navigate(['main/containers/' + containerId + '/' + clId], { queryParams: { isdetail: true } });
    }
  }

}
