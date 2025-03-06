import { Component, Injector, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { OFormComponent, OTextInputComponent, OTranslateService } from 'ontimize-web-ngx';

@Component({
  selector: 'app-containers-details',
  templateUrl: './containers-details.component.html',
  styleUrls: ['./containers-details.component.css']
})

export class ContainersDetailsComponent {

  @ViewChild('avgTemp', { static: false }) avgTemp!: OTextInputComponent;
  @ViewChild('maxTemp', { static: false }) maxTemp!: OTextInputComponent;
  @ViewChild('minTemp', { static: false }) minTemp!: OTextInputComponent;
  @ViewChild('avgHumidity', { static: false }) avgHumidity!: OTextInputComponent;
  @ViewChild('maxHumidity', { static: false }) maxHumidity!: OTextInputComponent;
  @ViewChild('minHumidity', { static: false }) minHumidity!: OTextInputComponent;
  @ViewChild('alertsCount', { static: false }) alertsCount!: OTextInputComponent;
  @ViewChild('formCNT', { static: false }) formCNT!: OFormComponent;

  translator: OTranslateService;

  constructor(private router: Router, protected injector: Injector) {
    this.translator = this.injector.get(OTranslateService);
  }

  public avgTempVisible: string = "N/A";
  public maxTempVisible: string = "N/A";
  public minTempVisible: string = "N/A";
  public avgHumidityVisible: string = "N/A";
  public maxHumidityVisible: string = "N/A";
  public minHumidityVisible: string = "N/A";
  public alertCountVisible: number = 0;

  fillData(e: any) {
    console.log(e);
    if (e.AVG_TEMP !== undefined) {
      this.avgTempVisible = this.avgTemp.getValue() + " ºC";
    }
    if (e.MAX_TEMP_DEV !== undefined) {
      this.maxTempVisible = this.maxTemp.getValue() + " ºC";
    }
    if (e.MIN_TEMP_DEV !== undefined) {
      this.minTempVisible = this.maxTemp.getValue() + " ºC";
    }
    if (e.AVG_HUMIDITY !== undefined) {
      this.avgHumidityVisible = this.avgHumidity.getValue() + "%";
    }
    if (e.MAX_HUMIDITY_DEV !== undefined) {
      this.maxHumidityVisible = this.maxHumidity.getValue() + "%";
    }
    if (e.MIN_HUMIDITY_DEV !== undefined) {
      this.minHumidityVisible = this.minHumidity.getValue() + "%";
    }
    if (e.ALT_COUNT !== undefined) {
      this.alertCountVisible = this.alertsCount.getValue();
    } else {
      this.alertCountVisible = 0;
    }
  }

  public translateLabel() {
    return this.translator.get("ALERTS_COUNT_TEXT").replace("#ALT_COUNT#", this.alertCountVisible.toString());
  }

  public openContainersLots(selected: any) {
    const row = selected.row;
    if (row && row.CL_ID) {
      this.router.navigate(['main', 'containers-lots', row.CL_ID], { queryParams: { isdetail: true } });
    }
  }
}