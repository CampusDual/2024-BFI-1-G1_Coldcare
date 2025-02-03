import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OntimizeWebModule } from 'ontimize-web-ngx';

import { ContainersRoutingModule } from './containers-routing.module';
import { ContainersHomeComponent } from './containers-home/containers-home.component';
import { ContainersNewComponent } from './containers-new/containers-new.component';
import { ContainersDetailsComponent } from './containers-details/containers-details.component';
import { ContainersLotsMeasurementsComponent } from './containers-lots-measurements/containers-lots-measurements.component';
import { ContainersBooleanRendererComponent } from './containers-boolean-renderer/containers-boolean-renderer.component';

@NgModule({
  declarations: [
    ContainersHomeComponent,
    ContainersNewComponent,
    ContainersDetailsComponent,
    ContainersLotsMeasurementsComponent,
    ContainersBooleanRendererComponent,
  ],
  imports: [
    CommonModule,
    OntimizeWebModule,
    ContainersRoutingModule
  ]
})
export class ContainersModule { }
