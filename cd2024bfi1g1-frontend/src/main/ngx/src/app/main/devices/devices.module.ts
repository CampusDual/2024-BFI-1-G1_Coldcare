import { NgModule } from '@angular/core';
import { OntimizeWebModule } from 'ontimize-web-ngx';

import { SharedModule } from '../../shared/shared.module';
import { DevicesRoutingModule } from './devices-routing.module';
import { HomeComponent } from './home/home.component';

@NgModule({
  imports: [
    SharedModule,
    OntimizeWebModule,
    DevicesRoutingModule
  ],
  declarations: [
    HomeComponent
  ]
})
export class DevicesModule { }
