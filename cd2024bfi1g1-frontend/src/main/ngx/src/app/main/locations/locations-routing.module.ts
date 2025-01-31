import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LocationsHomeComponent } from './locations-home/locations-home.component';
import { LocationsDetailComponent } from './locations-detail/locations-detail.component';
import { LocationsNewComponent } from './locations-new/locations-new.component';

const routes: Routes = [
  { path: '', component: LocationsHomeComponent },
  { path: 'new', component: LocationsNewComponent },
  { path: ':LOC_ID', component: LocationsDetailComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class LocationsRoutingModule { }
