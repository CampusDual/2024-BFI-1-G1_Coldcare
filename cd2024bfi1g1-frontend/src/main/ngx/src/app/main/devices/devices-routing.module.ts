import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { DevicesHomeComponent } from './devices-home/devices-home.component';

const routes: Routes = [
  {
    path: '',
    component: DevicesHomeComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DevicesRoutingModule { }
