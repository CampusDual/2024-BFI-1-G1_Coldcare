import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UsermicrosHomeComponent } from './usermicros-home/usermicros-home.component';

const routes: Routes = [
  { path: '', component: UsermicrosHomeComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UsermicrosRoutingModule { }
