import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OntimizeWebModule } from 'ontimize-web-ngx';

import { MedidasRoutingModule } from './medidas-routing.module';
import { MedidasHomeComponent } from './medidas-home/medidas-home.component';


@NgModule({
  declarations: [
    MedidasHomeComponent
  ],
  imports: [
    CommonModule,
    OntimizeWebModule,
    MedidasRoutingModule
  ]
})
export class MedidasModule { }
