import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DetailUsersComponent } from './detail/detail.component';
import { HomeUsersComponent } from './home/home.component';

const routes: Routes = [
  { path: '', component: HomeUsersComponent },
  { path: 'new', component: DetailUsersComponent },
  { path: ':USR_ID', component: DetailUsersComponent },

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UsersRoutingModule { }
