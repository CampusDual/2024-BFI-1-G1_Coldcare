import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OntimizeWebModule } from 'ontimize-web-ngx';
import { SharedModule } from '../../shared/shared.module';
import { VehiclesHomeComponent } from './vehicles-home/vehicles-home.component';
import { VehiclesRoutingModule } from './vehicles-routing.module';

@NgModule({
    declarations: [
        VehiclesHomeComponent
    ],

    imports: [
        SharedModule,
        CommonModule,
        OntimizeWebModule,
        VehiclesRoutingModule,
    ]
})
export class VehiclesModule { }
