import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OntimizeWebModule } from 'ontimize-web-ngx';

import { DevicesRoutingModule } from './devices-routing.module';
import { DevicesDetailComponent } from './devices-detail/devices-detail.component';
import { OChartModule } from 'ontimize-web-ngx-charts';


@NgModule({
  declarations: [
    DevicesDetailComponent
  ],
  imports: [
    CommonModule,
    OntimizeWebModule,
    DevicesRoutingModule,
    OChartModule
  ]
})
export class DevicesModule { }