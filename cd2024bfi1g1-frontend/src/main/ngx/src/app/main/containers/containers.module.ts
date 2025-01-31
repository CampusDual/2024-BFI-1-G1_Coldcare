import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OntimizeWebModule } from 'ontimize-web-ngx';

import { ContainersRoutingModule } from './containers-routing.module';
import { ContainersHomeComponent } from './containers-home/containers-home.component';
import { ContainersNewComponent } from './containers-new/containers-new.component';
import { ContainersDetailsComponent } from './containers-details/containers-details.component';
import { ContainersLotsMeasurementsComponent } from './containers-lots-measurements/containers-lots-measurements.component';
import { ContainersRepresentationComponent } from './containers-representation/containers-representation.component';
import { OMapModule } from 'ontimize-web-ngx-map';


@NgModule({
  declarations: [
    ContainersHomeComponent,
    ContainersNewComponent,
    ContainersDetailsComponent,
    ContainersLotsMeasurementsComponent,
    ContainersRepresentationComponent,

  ],
  imports: [
    CommonModule,
    OntimizeWebModule,
    ContainersRoutingModule,
    OMapModule
  ]
})
export class ContainersModule { }
