import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { OntimizeWebModule } from 'ontimize-web-ngx';
import { FilterComponent } from './components/filters/filters.component';
import { HomeToolbarComponent } from './components/home-toolbar/home-toolbar.component';
import { DevicesColumnRendererComponent } from '../main/devices/devices-column-renderer/devices-column-renderer.component';
import { IconStatusComponent } from './icon-status/icon-status.component';



@NgModule({
  imports: [
    OntimizeWebModule
  ],
  declarations: [
    FilterComponent,
    HomeToolbarComponent,
    DevicesColumnRendererComponent,
    IconStatusComponent
  ],
  exports: [
    CommonModule,
    FilterComponent,
    HomeToolbarComponent,
    DevicesColumnRendererComponent
  ]
})
export class SharedModule { }
