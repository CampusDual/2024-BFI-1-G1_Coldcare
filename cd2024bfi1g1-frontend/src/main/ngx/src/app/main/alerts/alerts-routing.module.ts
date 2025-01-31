import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AlertsHomeComponent } from './alerts-home/alerts-home.component';


export const CONTAINERS_MODULE_DECLARATIONS = [
  AlertsHomeComponent
]

const routes: Routes = [
  { path: '', component: AlertsHomeComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AlertsRoutingModule { }
