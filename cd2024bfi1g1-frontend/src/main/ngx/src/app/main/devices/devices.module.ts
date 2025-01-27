import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OntimizeWebModule } from 'ontimize-web-ngx';
import { SharedModule } from '../../shared/shared.module';
import { DevicesRoutingModule } from './devices-routing.module';
import { DevicesDetailComponent } from './devices-detail/devices-detail.component';
import { OChartModule } from 'ontimize-web-ngx-charts';
import { DevicesHomeComponent } from './devices-home/devices-home.component';
import { DevicesBooleanRendererComponent } from './devices-boolean-renderer/devices-boolean-renderer.component';

@NgModule({
  declarations: [
    DevicesDetailComponent,
    DevicesHomeComponent , 
    DevicesBooleanRendererComponent
  ],
    
  imports: [
    SharedModule,
    CommonModule,
    OntimizeWebModule,
    DevicesRoutingModule,
    OChartModule
  ]
})
export class DevicesModule { }
