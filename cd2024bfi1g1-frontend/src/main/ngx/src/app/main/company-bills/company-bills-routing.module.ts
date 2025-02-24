import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CompanyBillsHomeComponent } from './company-bills-home/company-bills-home.component';

const routes: Routes = [
  { path: '', component: CompanyBillsHomeComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CompanyBillsRoutingModule { }
