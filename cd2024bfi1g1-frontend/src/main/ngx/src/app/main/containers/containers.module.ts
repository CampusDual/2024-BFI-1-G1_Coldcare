import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OntimizeWebModule } from 'ontimize-web-ngx';
import { ContainersRoutingModule } from './containers-routing.module';
import { ContainersHomeComponent } from './containers-home/containers-home.component';
import { ContainersNewComponent } from './containers-new/containers-new.component';
import { ContainersDetailsComponent } from './containers-details/containers-details.component';
import { SharedModule } from "../../shared/shared.module";
import { ContainersHomeBooleanRendererComponent } from './containers-home-boolean-renderer/containers-home-boolean-renderer.component';

@NgModule({
  declarations: [
    ContainersHomeComponent,
    ContainersNewComponent,
    ContainersDetailsComponent,
    ContainersHomeBooleanRendererComponent
  ],
  imports: [
    CommonModule,
    OntimizeWebModule,
    ContainersRoutingModule,
    SharedModule
  ]
})
export class ContainersModule { }
