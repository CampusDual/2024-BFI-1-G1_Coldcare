import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OntimizeWebModule } from 'ontimize-web-ngx';
import { TransportsRoutingModule } from './transports-routing.module';
import { TransportsHomeComponent } from './transports-home/transports-home.component';
import { TransportsNewComponent } from './transports-new/transports-new.component';
import { TransportsDetailsComponent } from './transports-details/transports-details.component';
import { TransportsMapComponent } from './transports-map/transports-map.component';
import { OMapModule } from 'ontimize-web-ngx-map';


@NgModule({
  declarations: [
    TransportsHomeComponent,
    TransportsNewComponent,
    TransportsDetailsComponent,
    TransportsMapComponent
  ],
  imports: [
    CommonModule,
    OntimizeWebModule,
    TransportsRoutingModule,
    OMapModule
  ]
})
export class TransportsModule { }
