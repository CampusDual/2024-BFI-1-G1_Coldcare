import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { OntimizeWebModule } from "ontimize-web-ngx";

import { MedidasRoutingModule } from "./medidas-routing.module";
import { MedidasAdminComponent } from "./medidas-home/medidas-home.component";
import { SharedModule } from '../../../shared/shared.module';

@NgModule({
  declarations: [MedidasAdminComponent],
  imports: [CommonModule, OntimizeWebModule,
    SharedModule,
    MedidasRoutingModule],
})
export class MedidasModule {}
