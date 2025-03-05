import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OntimizeWebModule } from 'ontimize-web-ngx';

import { TransportersRoutingModule } from './transporters-routing.module';
import { TransportersHomeComponent } from './transporters-home/transporters-home.component';
import { TransportersDetailsComponent } from './transporters-details/transporters-details.component';
import { TransportersColumnRendererComponent } from './transporters-column-renderer/transporters-column-renderer.component';
import { SharedModule } from 'src/app/shared/shared.module';


@NgModule({
  declarations: [
    TransportersHomeComponent,
    TransportersDetailsComponent,
    TransportersColumnRendererComponent
  ],
  imports: [
    CommonModule,
    OntimizeWebModule,
    TransportersRoutingModule,
    SharedModule
  ]
})
export class TransportersModule { }
