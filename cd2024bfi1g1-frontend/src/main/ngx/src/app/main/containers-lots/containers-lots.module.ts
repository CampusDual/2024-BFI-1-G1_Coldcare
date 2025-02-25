import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OntimizeWebModule } from 'ontimize-web-ngx';
import { SharedModule } from "../../shared/shared.module";
import { ContainersLotsNewComponent } from './containers-lots-new/containers-lots-new.component';
import { ContainersLotsDetailsComponent } from './containers-lots-details/containers-lots-details.component';
import { ContainersLotsRoutingModule } from './containers-lots-routing.module';
import { ContainersTransfersNewOriginComponent } from './containers-transfers-new-origin/containers-transfers-new-origin.component';
import { ContainersTransfersNewDestinyComponent } from './containers-transfers-new-destiny/containers-transfers-new-destiny.component';



@NgModule({
  declarations: [
    ContainersLotsNewComponent,
    ContainersLotsDetailsComponent,
    ContainersTransfersNewOriginComponent,
    ContainersTransfersNewDestinyComponent
  ],
  imports: [
    CommonModule,
    OntimizeWebModule,
    ContainersLotsRoutingModule,
    SharedModule
  ]
})
export class ContainersLotsModule { }
