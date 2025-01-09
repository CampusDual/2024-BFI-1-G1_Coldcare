import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ContainersDetailsComponent } from './containers-details/containers-details.component';
import { ContainersHomeComponent } from './containers-home/containers-home.component';
import { ContainersNewComponent } from './containers-new/containers-new.component';

export const CONTAINERS_MODULE_DECLARATIONS = [
  ContainersDetailsComponent,
  ContainersHomeComponent,
  ContainersNewComponent
]

const routes: Routes = [
  { path: '', component: ContainersHomeComponent },
  { path: 'new', component: ContainersNewComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ContainersRoutingModule { }
