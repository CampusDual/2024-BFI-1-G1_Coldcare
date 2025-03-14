import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OntimizeWebModule } from 'ontimize-web-ngx';
import { TransportsRoutingModule } from './transports-routing.module';
import { TransportsHomeComponent } from './transports-home/transports-home.component';
import { TransportsNewComponent } from './transports-new/transports-new.component';
import { TransportsDetailsComponent } from './transports-details/transports-details.component';
import { TransportsColumnRendererComponent } from './transports-column-renderer/transports-column-renderer.component';
import { TransportsMapComponent } from './transports-map/transports-map.component';
import { OMapModule } from 'ontimize-web-ngx-map';
import { SharedModule } from "../../shared/shared.module";


@NgModule({
  declarations: [
    TransportsHomeComponent,
    TransportsNewComponent,
    TransportsDetailsComponent,
    TransportsColumnRendererComponent,
    TransportsColumnRendererComponent,
    TransportsMapComponent
  ],
  imports: [
    CommonModule,
    OntimizeWebModule,
    TransportsRoutingModule,
    OMapModule,
    SharedModule
]
})
export class TransportsModule { }
