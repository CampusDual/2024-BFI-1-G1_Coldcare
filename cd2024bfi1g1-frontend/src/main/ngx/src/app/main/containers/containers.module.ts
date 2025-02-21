import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OntimizeWebModule } from 'ontimize-web-ngx';

import { ContainersRoutingModule } from './containers-routing.module';
import { ContainersHomeComponent } from './containers-home/containers-home.component';
import { ContainersNewComponent } from './containers-new/containers-new.component';
import { ContainersDetailsComponent } from './containers-details/containers-details.component';
import { ContainersLotsMeasurementsComponent } from './containers-lots-measurements/containers-lots-measurements.component';
import { ContainersLotsAssignmentsComponent } from './containers-lots-assignments/containers-lots-assignments.component';
import { ContainersHomeBooleanRenderComponent } from './containers-home-boolean-render/containers-home-boolean-render.component';
import { SharedModule } from "../../shared/shared.module";

@NgModule({
  declarations: [
    ContainersHomeComponent,
    ContainersNewComponent,
    ContainersDetailsComponent,
    ContainersLotsMeasurementsComponent,
    ContainersLotsAssignmentsComponent,
    ContainersHomeBooleanRenderComponent
  ],
  imports: [
    CommonModule,
    OntimizeWebModule,
    ContainersRoutingModule,
    SharedModule
  ]
})
export class ContainersModule { }
