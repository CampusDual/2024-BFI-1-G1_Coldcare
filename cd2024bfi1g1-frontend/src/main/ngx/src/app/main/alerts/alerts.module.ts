import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OntimizeWebModule } from 'ontimize-web-ngx';

import { AlertsRoutingModule } from './alerts-routing.module';
import { AlertsHomeComponent } from './alerts-home/alerts-home.component';
import { AlertsDurationRendererComponent } from './alerts-duration-renderer/alerts-duration-renderer.component';
import { AlertsIsCurrentRenderComponent } from './alerts-is-current-render/alerts-is-current-render.component';

@NgModule({
  declarations: [
    AlertsHomeComponent,
    AlertsDurationRendererComponent,
    AlertsIsCurrentRenderComponent
  ],
  imports: [
    CommonModule,
    OntimizeWebModule,
    AlertsRoutingModule
  ]
})
export class AlertsModule { }
