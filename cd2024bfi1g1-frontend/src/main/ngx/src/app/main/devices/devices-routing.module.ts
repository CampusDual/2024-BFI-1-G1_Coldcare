import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DevicesDetailComponent } from './devices-detail/devices-detail.component';
import { DevicesHomeComponent } from './devices-home/devices-home.component';

const routes: Routes = [
  { path: '', component: DevicesHomeComponent },
  { path: ':DEV_ID', component: DevicesDetailComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DevicesRoutingModule { }
