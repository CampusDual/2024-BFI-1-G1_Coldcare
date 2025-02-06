import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { OntimizeWebModule } from "ontimize-web-ngx";

import { BillsRoutingModule } from "./bills-routing-module";
import { BillsHomeComponent } from "./bills-home/bills-home.component";


@NgModule({
    declarations: [BillsHomeComponent],
    imports: [CommonModule, OntimizeWebModule,

        BillsRoutingModule],
})
export class BillsModule { } 