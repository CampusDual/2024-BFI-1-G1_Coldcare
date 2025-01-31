import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";

import { AdminRoutingModule } from "./admin-routing.module";
import { OntimizeWebModule } from "ontimize-web-ngx";
import { BillsHomeComponent } from './bills/bills-home/bills-home.component';

@NgModule({
  declarations: [

  ],
  imports: [CommonModule, OntimizeWebModule, AdminRoutingModule],
})
export class AdminModule { }
