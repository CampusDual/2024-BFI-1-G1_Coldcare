import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ContainersHomeComponent } from './containers-home/containers-home.component';


const routes: Routes = [{
    path: '',
    component: ContainersHomeComponent
    }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ContainersRoutingModule { }
