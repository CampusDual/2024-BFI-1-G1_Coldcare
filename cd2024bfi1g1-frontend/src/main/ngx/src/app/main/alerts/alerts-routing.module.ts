import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AlertsHomeComponent } from './alerts-home/alerts-home.component';
import { AlertsDetailsComponent } from './alerts-details/alerts-details.component';


export const CONTAINERS_MODULE_DECLARATIONS = [
  AlertsHomeComponent
]

const routes: Routes = [
  { path: '', component: AlertsHomeComponent },
  { path: ':ALT_ID', component: AlertsDetailsComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AlertsRoutingModule { }
