import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { OntimizeWebModule } from 'ontimize-web-ngx';
import { FilterComponent } from './components/filters/filters.component';
import { HomeToolbarComponent } from './components/home-toolbar/home-toolbar.component';
import { DevicesColumnRendererComponent } from '../main/devices/devices-column-renderer/devices-column-renderer.component';
import { InDateRangeBooleanRendererComponent } from './components/in-date-range-boolean-renderer/in-date-range-boolean-renderer.component';
import { BillsMonthRendererComponent } from './components/bills-month-renderer/bills-month-renderer.component';
import { AlertBooleanRendererComponent } from './components/alert-boolean-renderer/alert-boolean-renderer.component';
import { MonthRendererPipe } from './components/pipes/month-renderer.pipe';
import { SecondsToTimePipe } from './components/pipes/seconds-to-time.pipe';
import { ContainersHomeBooleanRendererComponent, } from './components/containers-home-boolean-renderer/containers-home-boolean-renderer.component';
import { ContainerLotsBooleanRenderComponent } from './components/container-lots-boolean-render/container-lots-boolean-render.component';
import { LotsBooleanRenderComponent } from './components/lots-boolean-render/lots-boolean-render.component';


@NgModule({
  imports: [
    OntimizeWebModule
  ],
  declarations: [
    FilterComponent,
    HomeToolbarComponent,
    DevicesColumnRendererComponent,
    InDateRangeBooleanRendererComponent,
    BillsMonthRendererComponent,
    AlertBooleanRendererComponent,
    MonthRendererPipe,
    SecondsToTimePipe,
    ContainersHomeBooleanRendererComponent,
    ContainerLotsBooleanRenderComponent,
    LotsBooleanRenderComponent
  ],
  exports: [
    CommonModule,
    FilterComponent,
    HomeToolbarComponent,
    DevicesColumnRendererComponent,
    InDateRangeBooleanRendererComponent,
    BillsMonthRendererComponent,
    AlertBooleanRendererComponent,
    MonthRendererPipe,
    SecondsToTimePipe,
    ContainersHomeBooleanRendererComponent,
    ContainerLotsBooleanRenderComponent,
    LotsBooleanRenderComponent
  ]
})
export class SharedModule { }
