import { Component, Injector, ViewChild } from '@angular/core';
import { DialogService, OComboComponent, OntimizeService, ORealInputComponent } from 'ontimize-web-ngx';
import { Router } from '@angular/router';

@Component({
  selector: 'app-lots-details',
  templateUrl: './lots-details.component.html',
  styleUrls: ['./lots-details.component.css']
})
export class LotsDetailsComponent {

  @ViewChild('productCombo', { static: false }) productCombo!: OComboComponent;
  @ViewChild('minTempInput', { static: false }) minTempInput!: ORealInputComponent;
  @ViewChild('maxTempInput', { static: false }) maxTempInput!: ORealInputComponent;

  graphData: any[] = [];
  protected service: OntimizeService;

  constructor(
    protected injector: Injector,
    private router: Router,
    private dialogService: DialogService,
  ) {
    this.service = this.injector.get(OntimizeService);
  }

  updateGraph(event: any) {
    const lotId = event.LOT_ID;
    const columns = [
      'CNT_ID',
      'CL_ID',
      'CL_START_DATE',
      'CL_ID_ORIGIN',
      'CL_ID_DESTINY',
      'CNT_NAME',
      'HAS_ALERT'
    ];
    const filter = { LOT_ID: Number(lotId) };

    const conf = this.service.getDefaultServiceConfiguration('containersLots');
    this.service.configureService(conf);

    this.service.query(filter, columns, 'clWithOriginDestination').subscribe(response => {
      if (response.code === 0) {
        this.graphData = response.data || [];
      } else {
        this.dialogService.alert('ERROR_TITLE', 'ERROR_MSG_GRAPH_QUERY');
      }
    })
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