import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { OntimizeWebModule } from "ontimize-web-ngx";

import { BillsRoutingModule } from "./bills-routing-module";
import { BillsComponent } from "./bills.component";
import { SharedModule } from '../../../shared/shared.module';

@NgModule({
    declarations: [BillsComponent],
    imports: [CommonModule, OntimizeWebModule,
        SharedModule,
        BillsRoutingModule],
})
export class BillsModule { } 