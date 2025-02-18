import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { LotsHomeComponent } from './lots-home/lots-home.component';
import { LotsNewComponent } from './lots-new/lots-new.component';
import { LotsDetailsComponent } from './lots-details/lots-details.component';
import { ContainersLotsMeasurementsComponent } from '../containers/containers-lots-measurements/containers-lots-measurements.component';

const routes: Routes = [
    { path: '', component: LotsHomeComponent },
    { path: 'new', component: LotsNewComponent},
    { path: ':LOT_ID', component: LotsDetailsComponent},
    { path: ':CNT_ID/:CL_ID', component: ContainersLotsMeasurementsComponent },
  ];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class LotsRoutingModule { }
