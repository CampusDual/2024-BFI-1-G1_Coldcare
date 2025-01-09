import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OntimizeWebModule } from 'ontimize-web-ngx';

import { DevicesRoutingModule } from './devices-routing.module';
import { DevicesGraphComponent } from './devices-graph/devices-graph.component';


@NgModule({
  declarations: [
    DevicesGraphComponent
  ],
  imports: [
    CommonModule,
    OntimizeWebModule,
    DevicesRoutingModule
  ]
})
export class DevicesModule { }
