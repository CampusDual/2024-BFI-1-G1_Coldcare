import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OntimizeWebModule } from 'ontimize-web-ngx';

import { DevicesRoutingModule } from './devices-routing.module';
import { DevicesDetailComponent } from './devices-detail/devices-detail.component';


@NgModule({
  declarations: [
    DevicesDetailComponent
  ],
  imports: [
    CommonModule,
    OntimizeWebModule,
    DevicesRoutingModule
  ]
})
export class DevicesModule { }
