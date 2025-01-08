import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ContainersRoutingModule } from './containers-routing.module';
import { ContainersComponent } from './containers.component';
import { ContainersHomeComponent } from './containers-home/containers-home.component';


@NgModule({
  declarations: [
    ContainersComponent,
    ContainersHomeComponent
  ],
  imports: [
    CommonModule,
    ContainersRoutingModule
  ]
})
export class ContainersModule { }
