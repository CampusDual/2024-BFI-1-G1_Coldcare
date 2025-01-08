import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DevicesDetailComponent } from './devices-detail/devices-detail.component';


const routes: Routes = [{
  path: '',
  component: DevicesDetailComponent
  }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DevicesRoutingModule { }
