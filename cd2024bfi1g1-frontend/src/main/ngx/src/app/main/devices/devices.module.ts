import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OntimizeWebModule } from 'ontimize-web-ngx';

import { DevicesRoutingModule } from './devices-routing.module';
import { DevicesGraphComponent } from './devices-graph/devices-graph.component';
import { OChartModule } from 'ontimize-web-ngx-charts';



@NgModule({
  declarations: [
    DevicesGraphComponent
  ],
  imports: [
    CommonModule,
    OntimizeWebModule,
    DevicesRoutingModule,
    OChartModule
  ]
})
export class DevicesModule { }
