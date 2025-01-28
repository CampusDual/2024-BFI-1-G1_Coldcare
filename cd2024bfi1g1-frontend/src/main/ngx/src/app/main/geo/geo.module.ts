import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";

import { GeoRoutingModule } from "./geo-routing.module";
import { OntimizeWebModule } from "ontimize-web-ngx";

@NgModule({
    declarations: [],
    imports: [CommonModule, OntimizeWebModule, GeoRoutingModule],
})
export class GeoModule { }