import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DevicesGraphComponent } from './devices-graph/devices-graph.component';


const routes: Routes = [
  {path: ':DEV_ID', component: DevicesGraphComponent}
 ];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DevicesRoutingModule { }
