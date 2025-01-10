import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DwuHomeComponent } from './dwu-home/dwu-home.component';

const routes: Routes = [{
    path: '',
  component: DwuHomeComponent
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DevicesWithoutUsersRoutingModule { }
