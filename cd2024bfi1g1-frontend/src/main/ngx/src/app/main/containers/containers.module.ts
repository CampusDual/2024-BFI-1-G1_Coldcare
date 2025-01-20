import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OntimizeWebModule } from 'ontimize-web-ngx';

import { ContainersRoutingModule } from './containers-routing.module';
import { ContainersHomeComponent } from './containers-home/containers-home.component';
import { ContainersNewComponent } from './containers-new/containers-new.component';
import { ContainersDetailsComponent } from './containers-details/containers-details.component';
import { ContainersDevicesComponent } from './containers-devices/containers-devices.component';
import { ContainersEditComponent } from './containers-edit/containers-edit.component';


@NgModule({
  declarations: [
    ContainersHomeComponent,
    ContainersNewComponent,
    ContainersDetailsComponent,
    ContainersDevicesComponent,
    ContainersEditComponent,
  ],
  imports: [
    CommonModule,
    OntimizeWebModule,
    ContainersRoutingModule
  ]
})
export class ContainersModule { }
