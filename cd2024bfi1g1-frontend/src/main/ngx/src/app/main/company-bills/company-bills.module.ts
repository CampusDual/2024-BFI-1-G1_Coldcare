import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { CompanyBillsRoutingModule } from "./company-bills-routing.module";
import { OntimizeWebModule } from "ontimize-web-ngx";
import { CompanyBillsHomeComponent } from "./company-bills-home/company-bills-home.component";
import { SharedModule } from "src/app/shared/shared.module";

@NgModule({
  declarations: [CompanyBillsHomeComponent],
  imports: [
    CommonModule,
    OntimizeWebModule,
    CompanyBillsRoutingModule,
    SharedModule
  ],
})
export class CompanyBillsModule { }
