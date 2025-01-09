import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OntimizeWebModule } from 'ontimize-web-ngx';

import { ContainersRoutingModule } from './containers-routing.module';
import { ContainersHomeComponent } from './containers-home/containers-home.component';


@NgModule({
  declarations: [
    ContainersHomeComponent,
  ],
  imports: [
    CommonModule,
    OntimizeWebModule,
    ContainersRoutingModule
  ]
})
export class ContainersModule { }
