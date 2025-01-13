import { NgModule } from '@angular/core';
import { OntimizeWebModule } from 'ontimize-web-ngx';

import { SharedModule } from '../../shared/shared.module';
import { DevicesRoutingModule } from './devices-routing.module';

import { DevicesHomeComponent } from './devices-home/devices-home.component';

@NgModule({
  imports: [
    SharedModule,
    OntimizeWebModule,
    DevicesRoutingModule
  ],
  declarations: [
    DevicesHomeComponent
  ]
})
export class DevicesModule { }
