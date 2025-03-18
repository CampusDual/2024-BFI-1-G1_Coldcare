import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TransportsHomeComponent } from './transports-home/transports-home.component';
import { TransportsNewComponent } from './transports-new/transports-new.component';
import { TransportsDetailsComponent } from './transports-details/transports-details.component';

const routes: Routes = [
  { path: '', component: TransportsHomeComponent },
  { path: 'new', component: TransportsNewComponent},
  { path: ':TRP_ID', component: TransportsDetailsComponent},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TransportsRoutingModule { }
