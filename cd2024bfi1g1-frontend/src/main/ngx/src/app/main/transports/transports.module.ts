import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OntimizeWebModule } from 'ontimize-web-ngx';
import { TransportsRoutingModule } from './transports-routing.module';
import { TransportsHomeComponent } from './transports-home/transports-home.component';
import { TransportsNewComponent } from './transports-new/transports-new.component';
import { TransportsDetailsComponent } from './transports-details/transports-details.component';
import { TransportColumnRendererComponent } from './transport-column-renderer/transport-column-renderer.component';


@NgModule({
  declarations: [
    TransportsHomeComponent,
    TransportsNewComponent,
    TransportsDetailsComponent,
    TransportColumnRendererComponent
  ],
  imports: [
    CommonModule,
    OntimizeWebModule,
    TransportsRoutingModule
  ]
})
export class TransportsModule { }
