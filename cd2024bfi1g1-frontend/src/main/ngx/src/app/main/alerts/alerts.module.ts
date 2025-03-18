import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OntimizeWebModule } from 'ontimize-web-ngx';
import { SharedModule } from "src/app/shared/shared.module";
import { OChartModule } from 'ontimize-web-ngx-charts';
import { AlertsRoutingModule } from './alerts-routing.module';
import { AlertsHomeComponent } from './alerts-home/alerts-home.component';
import { AlertsDurationRendererComponent } from './alerts-duration-renderer/alerts-duration-renderer.component';
import { AlertsDetailsComponent } from './alerts-details/alerts-details.component';


@NgModule({
  declarations: [
    AlertsHomeComponent,
    AlertsDurationRendererComponent,
    AlertsDetailsComponent,
  ],
  imports: [
    CommonModule,
    OntimizeWebModule,
    AlertsRoutingModule,
    SharedModule,
    OChartModule
  ]
})
export class AlertsModule { }
