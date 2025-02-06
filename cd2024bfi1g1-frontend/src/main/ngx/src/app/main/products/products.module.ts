import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { OntimizeWebModule } from "ontimize-web-ngx";

import { ProductsRoutingModule } from './products-routing.module';
import { ProductsNewComponent } from './products-new/products-new.component';
import { ProductsHomeComponent } from './products-home/products-home.component';
import { SharedModule } from '../../shared/shared.module';



@NgModule({
  declarations: [
    ProductsNewComponent,
    ProductsHomeComponent,
  ],
  imports: [CommonModule,
    OntimizeWebModule,
    SharedModule,
    ProductsRoutingModule],
})
export class ProductsModule { }
