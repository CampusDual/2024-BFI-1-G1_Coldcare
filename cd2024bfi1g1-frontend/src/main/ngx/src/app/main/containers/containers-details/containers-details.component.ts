import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { OTextInputComponent } from 'ontimize-web-ngx';

@Component({
  selector: 'app-containers-details',
  templateUrl: './containers-details.component.html',
  styleUrls: ['./containers-details.component.css']
})

export class ContainersDetailsComponent implements OnInit {

  @ViewChild('AVGT', { static: false }) AVGT: OTextInputComponent;
  @ViewChild('MAXT', { static: false }) MAXT: OTextInputComponent;
  @ViewChild('MINT', { static: false }) MINT: OTextInputComponent;
  @ViewChild('AVGH', { static: false }) AVGH: OTextInputComponent;
  @ViewChild('MAXH', { static: false }) MAXH: OTextInputComponent;
  @ViewChild('MINH', { static: false }) MINH: OTextInputComponent;
  @ViewChild('ALERTS', { static: false }) ALERTS: OTextInputComponent;

  ngOnInit(): void {
    setInterval(() => {
      this.AVGT.setValue("N/A", { emitModelToViewValueChange: false });
      this.MAXT.setValue("N/A", { emitModelToViewValueChange: false });
      this.MINT.setValue("N/A", { emitModelToViewValueChange: false });
      this.AVGH.setValue("N/A", { emitModelToViewValueChange: false });
      this.MAXH.setValue("N/A", { emitModelToViewValueChange: false });
      this.MINH.setValue("N/A", { emitModelToViewValueChange: false });
      this.ALERTS.setValue("0", { emitModelToViewValueChange: false });
    }, 1000);
  }

  constructor(
    private router: Router
  ) { }

  public openContainersLots(selected: any) {
    const row = selected.row;
    if (row && row.CL_ID) {
      this.router.navigate(['main', 'containers-lots', row.CL_ID], { queryParams: { isdetail: true } });
    }
  }
}