import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OntimizeWebModule } from 'ontimize-web-ngx';

import { PlansHomeComponent } from './plans-home/plans-home.component';
import { PlansRoutingModule } from './plans-routing.module';
import { PlansNewComponent } from './plans-new/plans-new.component';
import { PlansDetailComponent } from './plans-detail/plans-detail.component';
import { PlansPricesNewComponent } from './plans-prices-new/plans-prices-new.component';


@NgModule({
  declarations: [
    PlansHomeComponent,
    PlansNewComponent,
    PlansDetailComponent,
    PlansPricesNewComponent
  ],
  imports: [
    CommonModule,
    OntimizeWebModule,
    PlansRoutingModule
  ]
})
export class PlansModule { }