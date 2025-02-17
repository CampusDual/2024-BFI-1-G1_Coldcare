import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OntimizeWebModule } from 'ontimize-web-ngx';

import { ContainersRoutingModule } from './containers-routing.module';
import { ContainersHomeComponent } from './containers-home/containers-home.component';
import { ContainersNewComponent } from './containers-new/containers-new.component';
import { ContainersDetailsComponent } from './containers-details/containers-details.component';
import { ContainersLotsMeasurementsComponent } from './containers-lots-measurements/containers-lots-measurements.component';
import { ContainersLotsAssignmentsComponent } from './containers-lots-assignments/containers-lots-assignments.component';
import { ContainersLotsBooleanRendererComponent } from './containers-lots-boolean-renderer/containers-lots-boolean-renderer.component';
import { ContainersHomeBooleanRenderComponent } from './containers-home-boolean-render/containers-home-boolean-render.component';
import { ContainersTransfersNewOriginComponent } from './containers-transfers-new-origin/containers-transfers-new-origin.component';
import { ContainersTransfersNewDestinyComponent } from './containers-transfers-new-destiny/containers-transfers-new-destiny.component';

@NgModule({
  declarations: [
    ContainersHomeComponent,
    ContainersNewComponent,
    ContainersDetailsComponent,
    ContainersLotsMeasurementsComponent,
    ContainersLotsAssignmentsComponent,
    ContainersLotsBooleanRendererComponent,
    ContainersHomeBooleanRenderComponent,
    ContainersTransfersNewOriginComponent,
    ContainersTransfersNewDestinyComponent
  ],
  imports: [
    CommonModule,
    OntimizeWebModule,
    ContainersRoutingModule
  ]
})
export class ContainersModule { }
