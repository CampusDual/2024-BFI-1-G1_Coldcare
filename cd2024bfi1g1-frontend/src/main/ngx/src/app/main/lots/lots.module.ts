import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OntimizeWebModule } from 'ontimize-web-ngx';
import { LotsRoutingModule } from './lots-routing.module';
import { LotsHomeComponent } from './lots-home/lots-home.component';
import { LotsNewComponent } from './lots-new/lots-new.component';
import { LotsDetailsComponent } from './lots-details/lots-details.component';
import { SharedModule } from 'src/app/shared/shared.module';



@NgModule({
  declarations: [
    LotsHomeComponent,
    LotsNewComponent,
    LotsDetailsComponent
  ],
  imports: [
    CommonModule,
    OntimizeWebModule,
    LotsRoutingModule,
    SharedModule
  ]
})
export class LotsModule { }
