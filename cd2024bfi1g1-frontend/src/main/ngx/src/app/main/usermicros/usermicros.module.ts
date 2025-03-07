import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UsermicrosRoutingModule } from './usermicros-routing.module';
import { UsermicrosHomeComponent } from './usermicros-home/usermicros-home.component';
import { OntimizeWebModule } from 'ontimize-web-ngx';



@NgModule({
  declarations: [
    UsermicrosHomeComponent
  ],
  imports: [
    CommonModule,
    UsermicrosRoutingModule,
    OntimizeWebModule
  ]
})
export class UsermicrosModule { }
