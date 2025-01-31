import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OntimizeWebModule } from 'ontimize-web-ngx';
import { SharedModule } from '../../shared/shared.module';
import { LocationsRoutingModule } from './locations-routing.module';
import { LocationsHomeComponent } from './locations-home/locations-home.component';
import { LocationsDetailComponent } from './locations-detail/locations-detail.component';
import { LocationsNewComponent } from './locations-new/locations-new.component';

@NgModule({
  declarations: [
    LocationsHomeComponent,
    LocationsDetailComponent,
    LocationsNewComponent
  ],

  imports: [
    SharedModule,
    CommonModule,
    OntimizeWebModule,
    LocationsRoutingModule,
  ]
})
export class LocationsModule { }
