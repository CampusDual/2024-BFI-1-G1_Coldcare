import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OntimizeWebModule } from 'ontimize-web-ngx';

import { TransportersRoutingModule } from './transporters-routing.module';
import { TransportersHomeComponent } from './transporters-home/transporters-home.component';
import { TransportersDetailsComponent } from './transporters-details/transporters-details.component';
import { SharedModule } from 'src/app/shared/shared.module';


@NgModule({
  declarations: [
    TransportersHomeComponent,
    TransportersDetailsComponent,
  ],
  imports: [
    CommonModule,
    OntimizeWebModule,
    TransportersRoutingModule,
    SharedModule
  ]
})
export class TransportersModule { }
