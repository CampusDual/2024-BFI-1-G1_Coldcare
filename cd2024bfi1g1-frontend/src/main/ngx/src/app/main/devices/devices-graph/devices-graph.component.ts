import { Component, ViewChild } from '@angular/core';
import { OChartComponent, LineChartConfiguration, ChartService } from 'ontimize-web-ngx-charts';

@Component({
  selector: 'app-devices-graph',
  templateUrl: './devices-graph.component.html',
  styleUrls: ['./devices-graph.component.css']
})
export class DevicesGraphComponent {
  chartParameters: LineChartConfiguration;

  constructor() {
    this.chartParameters = new LineChartConfiguration();
    this.chartParameters.isArea = [true];
    this.chartParameters.interactive = false;
    this.chartParameters.useInteractiveGuideline = false;
  }
}
