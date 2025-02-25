import { Component, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { OFormComponent } from 'ontimize-web-ngx';

@Component({
  selector: 'app-containers-details',
  templateUrl: './containers-details.component.html',
  styleUrls: ['./containers-details.component.css']
})

export class ContainersDetailsComponent {

  @ViewChild("formCNT") formCNT: OFormComponent;

  constructor(
    private router: Router
  ) { }

  public openContainersLots(selected: any) {
    const row = selected.row;
    if (row && row.CL_ID) {
      this.router.navigate(['main', 'containers-lots', row.CL_ID], { queryParams: { isdetail: true } });
    }
  }

  public newCL() {
    this.router.navigate(['main', 'containers-lots', 'new'], { queryParams: { CNT_ID: this.formCNT.getKeysValues().CNT_ID } });
  }
}