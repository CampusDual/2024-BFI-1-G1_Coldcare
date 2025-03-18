import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ContainersLotsDetailsComponent } from './containers-lots-details/containers-lots-details.component';
import { ContainersTransfersNewOriginComponent } from './containers-transfers-new-origin/containers-transfers-new-origin.component';
import { ContainersTransfersNewDestinyComponent } from './containers-transfers-new-destiny/containers-transfers-new-destiny.component';

export const CONTAINERS_LOTS_MODULE_DECLARATIONS = [
  ContainersLotsDetailsComponent,
  ContainersTransfersNewOriginComponent,
  ContainersTransfersNewDestinyComponent
]

const routes: Routes = [
  { path: ':CL_ID', component: ContainersLotsDetailsComponent },
  { path: ':CL_ID/newOrigin/new', component: ContainersTransfersNewOriginComponent },
  { path: ':CL_ID/newDestiny/new', component: ContainersTransfersNewDestinyComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ContainersLotsRoutingModule { }
