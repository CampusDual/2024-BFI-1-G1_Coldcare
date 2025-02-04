import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ContainersDetailsComponent } from './containers-details/containers-details.component';
import { ContainersHomeComponent } from './containers-home/containers-home.component';
import { ContainersNewComponent } from './containers-new/containers-new.component';
import { ContainersLotsMeasurementsComponent } from './containers-lots-measurements/containers-lots-measurements.component';
import { ContainersLotsAssignmentsComponent } from './containers-lots-assignments/containers-lots-assignments.component';

export const CONTAINERS_MODULE_DECLARATIONS = [
  ContainersDetailsComponent,
  ContainersHomeComponent,
  ContainersNewComponent,
  ContainersLotsMeasurementsComponent,
  ContainersLotsAssignmentsComponent
]

const routes: Routes = [
  { path: '', component: ContainersHomeComponent },
  { path: 'new', component: ContainersNewComponent },
  { path: ':CNT_ID', component: ContainersDetailsComponent },
  { path: ':CNT_ID/new', component: ContainersLotsAssignmentsComponent },
  { path: ':CNT_ID/:CL_ID', component: ContainersLotsMeasurementsComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ContainersRoutingModule { }
