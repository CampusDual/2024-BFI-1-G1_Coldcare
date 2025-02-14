import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OntimizeWebModule } from 'ontimize-web-ngx';

import { TransportersRoutingModule } from './transporters-routing.module';
import { TransportersHomeComponent } from './transporters-home/transporters-home.component';


@NgModule({
  declarations: [
    TransportersHomeComponent
  ],
  imports: [
    CommonModule,
    OntimizeWebModule,
    TransportersRoutingModule
  ]
})
export class TransportersModule { }
