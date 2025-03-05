import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { OntimizeWebModule } from "ontimize-web-ngx";

import { BillsRoutingModule } from "./bills-routing-module";
import { BillsHomeComponent } from "./bills-home/bills-home.component";
import { BillsMonthRendererComponent } from '../../../shared/components/bills-month-renderer/bills-month-renderer.component';
import { SharedModule } from "src/app/shared/shared.module";
import { BillsDetailsComponent } from './bills-details/bills-details.component';


@NgModule({
    declarations: [
        BillsHomeComponent,
        BillsDetailsComponent
    ],
    imports: [
        CommonModule,
        OntimizeWebModule,
        BillsRoutingModule,
        SharedModule
    ],
})
export class BillsModule { } 