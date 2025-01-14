import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DwuHomeComponent } from './dwu-home/dwu-home.component';
import { DevicesAssignmentsComponent } from './devices-assignments/devices-assignments.component';
const routes: Routes = [
  { path: '', component: DwuHomeComponent },
  { path: ':DEV_ID', component: DevicesAssignmentsComponent },  
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DevicesWithoutUsersRoutingModule { }
