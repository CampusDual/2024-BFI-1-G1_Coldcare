import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CompaniesHomeComponent } from './companies-home/companies-home.component';

const routes: Routes = [
  { path: '', component: CompaniesHomeComponent },  
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CompaniesRoutingModule { }
