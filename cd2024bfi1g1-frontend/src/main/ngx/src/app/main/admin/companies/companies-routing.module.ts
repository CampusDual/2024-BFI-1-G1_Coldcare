import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CompaniesHomeComponent } from './companies-home/companies-home.component';
import { CompaniesNewComponent } from './companies-new/companies-new.component';

const routes: Routes = [
  { path: '', component: CompaniesHomeComponent },  
  { path: 'new', component: CompaniesNewComponent },  
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CompaniesRoutingModule { }
