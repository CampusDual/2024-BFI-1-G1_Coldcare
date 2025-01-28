import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";

import { GeoRoutingModule } from "./geo-routing.module";
import { OntimizeWebModule } from "ontimize-web-ngx";
import { OMapModule } from 'ontimize-web-ngx-map';

@NgModule({
    declarations: [],
    imports: [CommonModule, OntimizeWebModule, OMapModule, GeoRoutingModule],
})
export class GeoModule { }