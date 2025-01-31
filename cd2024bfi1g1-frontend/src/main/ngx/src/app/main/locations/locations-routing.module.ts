import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LocationsHomeComponent } from './locations-home/locations-home.component';
import { LocationsDetailComponent } from './locations-detail/locations-detail.component';

const routes: Routes = [
  { path: '', component: LocationsHomeComponent },
  { path: ':LOC_ID', component: LocationsDetailComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class LocationsRoutingModule { }
