import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OntimizeWebModule } from 'ontimize-web-ngx';
import { TransportsRoutingModule } from './transports-routing.module';
import { TransportsHomeComponent } from './transports-home/transports-home.component';
import { TransportsNewComponent } from './transports-new/transports-new.component';
import { TransportsDetailsComponent } from './transports-details/transports-details.component';


@NgModule({
  declarations: [
    TransportsHomeComponent,
    TransportsNewComponent,
    TransportsDetailsComponent
  ],
  imports: [
    CommonModule,
    OntimizeWebModule,
    TransportsRoutingModule
  ]
})
export class TransportsModule { }
