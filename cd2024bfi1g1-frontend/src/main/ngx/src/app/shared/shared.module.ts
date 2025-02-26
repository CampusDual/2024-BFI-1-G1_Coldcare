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
    MonthRendererPipe
  ],
  exports: [
    CommonModule,
    FilterComponent,
    HomeToolbarComponent,
    DevicesColumnRendererComponent,
    InDateRangeBooleanRendererComponent,
    BillsMonthRendererComponent,
    AlertBooleanRendererComponent,
    MonthRendererPipe
  ]
})
export class SharedModule { }
