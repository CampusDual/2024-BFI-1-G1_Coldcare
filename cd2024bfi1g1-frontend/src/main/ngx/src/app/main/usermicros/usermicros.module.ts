import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UsermicrosRoutingModule } from './usermicros-routing.module';
import { UsermicrosHomeComponent } from './usermicros-home/usermicros-home.component';


@NgModule({
  declarations: [
    UsermicrosHomeComponent
  ],
  imports: [
    CommonModule,
    UsermicrosRoutingModule
  ]
})
export class UsermicrosModule { }
