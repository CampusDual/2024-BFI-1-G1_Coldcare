import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { OntimizeWebModule } from "ontimize-web-ngx";

import { ProductsRoutingModule } from './products-routing.module';
import { ProductsNewComponent } from './products-new/products-new.component';
import { ProductsHomeComponent } from './products-home/products-home.component';
import { SharedModule } from '../../shared/shared.module';
import { ProductsDetailComponent } from './products-detail/products-detail.component';



@NgModule({
  declarations: [
    ProductsNewComponent,
    ProductsHomeComponent,
    ProductsDetailComponent,
  ],
  imports: [CommonModule,
    OntimizeWebModule,
    SharedModule,
    ProductsRoutingModule],
})
export class ProductsModule { }
