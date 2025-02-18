import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { OntimizeWebModule } from "ontimize-web-ngx";

import { BillsRoutingModule } from "./bills-routing-module";
import { BillsHomeComponent } from "./bills-home/bills-home.component";
import { BillsMonthRendererComponent } from './bills-month-renderer/bills-month-renderer.component';


@NgModule({
    declarations: [BillsHomeComponent, BillsMonthRendererComponent],
    imports: [
        CommonModule,
        OntimizeWebModule,
        BillsRoutingModule
    ],
})
export class BillsModule { } 