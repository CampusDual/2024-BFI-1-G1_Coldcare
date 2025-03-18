import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CompaniesHomeComponent } from './companies-home/companies-home.component';
import { CompaniesNewComponent } from './companies-new/companies-new.component';
import { CompaniesDetailsComponent } from './companies-details/companies-details.component';

const routes: Routes = [
  { path: '', component: CompaniesHomeComponent },
  { path: 'new', component: CompaniesNewComponent },
  { path: ':CMP_ID', component: CompaniesDetailsComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CompaniesRoutingModule { }
