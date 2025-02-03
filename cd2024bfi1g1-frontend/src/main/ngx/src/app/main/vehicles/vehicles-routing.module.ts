import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { VehiclesHomeComponent } from './vehicles-home/vehicles-home.component';
import { VehiclesNewComponent } from './vehicles-new/vehicles-new.component';
import { VehiclesDetailsComponent } from './vehicles-details/vehicles-details.component';

const routes: Routes = [
    { path: '', component: VehiclesHomeComponent },
    { path: 'new', component: VehiclesNewComponent },
    { path: ':VHC_ID', component: VehiclesDetailsComponent }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class VehiclesRoutingModule { }
