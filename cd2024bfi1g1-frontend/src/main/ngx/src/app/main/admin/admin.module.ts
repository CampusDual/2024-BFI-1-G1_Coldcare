import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";

import { AdminRoutingModule } from "./admin-routing.module";
import { OntimizeWebModule } from "ontimize-web-ngx";
import { BillsComponent } from './bills/bills.component';

@NgModule({
  declarations: [
    BillsComponent
  ],
  imports: [CommonModule, OntimizeWebModule, AdminRoutingModule],
})
export class AdminModule {}
