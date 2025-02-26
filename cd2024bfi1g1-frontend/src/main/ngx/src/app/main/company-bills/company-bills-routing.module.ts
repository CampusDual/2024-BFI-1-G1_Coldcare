import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CompanyBillsHomeComponent } from './company-bills-home/company-bills-home.component';
import { CompanyBillsDetailsComponent } from './company-bills-details/company-bills-details.component';

const routes: Routes = [
  { path: '', component: CompanyBillsHomeComponent },
  { path: ':BIL_ID', component: CompanyBillsDetailsComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CompanyBillsRoutingModule { }
