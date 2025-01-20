import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OntimizeWebModule } from 'ontimize-web-ngx';

import { CompaniesHomeComponent } from './companies-home/companies-home.component';
import { CompaniesRoutingModule } from './companies-routing.module';


@NgModule({
  declarations: [
    CompaniesHomeComponent
  ],
  imports: [
    CommonModule,
    OntimizeWebModule,
    CompaniesRoutingModule
  ]
})
export class CompaniesModule { }
