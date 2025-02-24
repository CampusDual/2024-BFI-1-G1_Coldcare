import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TransportersHomeComponent } from './transporters-home/transporters-home.component';
import { TransportersDetailsComponent } from './transporters-details/transporters-details.component';

const routes: Routes = [
  { path: '', component: TransportersHomeComponent },
  { path: ':TRP_ID', component: TransportersDetailsComponent},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TransportersRoutingModule { }
