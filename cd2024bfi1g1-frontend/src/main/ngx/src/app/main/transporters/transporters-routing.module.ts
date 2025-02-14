import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TransportersHomeComponent } from './transporters-home/transporters-home.component';

const routes: Routes = [
  {
    path: '',
  component: TransportersHomeComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TransportersRoutingModule { }
