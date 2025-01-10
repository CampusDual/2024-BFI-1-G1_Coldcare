import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OntimizeWebModule } from 'ontimize-web-ngx';

import { DevicesWithoutUsersRoutingModule } from './devices-without-users-routing.module';
import { DwuHomeComponent } from './dwu-home/dwu-home.component';


@NgModule({
  declarations: [
    DwuHomeComponent
  ],
  imports: [
    CommonModule,
    OntimizeWebModule,
    DevicesWithoutUsersRoutingModule
  ]
})
export class DevicesWithoutUsersModule { }
