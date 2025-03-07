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
import { DateTransformPipe } from './components/pipes/date-transform.pipe';
import { StatusIconPipe } from './components/pipes/status-icon.pipe';
import { DateRendererPipe } from './components/pipes/date-renderer.pipe';
import { SecondsToTimePipe } from './components/pipes/seconds-to-time.pipe';
import { DatePipe } from '@angular/common';


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
    DateTransformPipe,
    StatusIconPipe,
    DateRendererPipe,
    SecondsToTimePipe
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
    DateTransformPipe,
    StatusIconPipe,
    DateRendererPipe,
    SecondsToTimePipe
  ],
  providers: [
    DatePipe
  ],
})
export class SharedModule { }
