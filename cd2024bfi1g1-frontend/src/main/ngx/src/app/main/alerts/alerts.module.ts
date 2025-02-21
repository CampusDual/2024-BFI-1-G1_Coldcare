import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OntimizeWebModule } from 'ontimize-web-ngx';

import { AlertsRoutingModule } from './alerts-routing.module';
import { AlertsHomeComponent } from './alerts-home/alerts-home.component';
import { AlertsDurationRendererComponent } from './alerts-duration-renderer/alerts-duration-renderer.component';

@NgModule({
  declarations: [
    AlertsHomeComponent,
    AlertsDurationRendererComponent
  ],
  imports: [
    CommonModule,
    OntimizeWebModule,
    AlertsRoutingModule
  ]
})
export class AlertsModule { }
