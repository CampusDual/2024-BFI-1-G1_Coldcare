import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MedidasHomeComponent } from './medidas-home/medidas-home.component';


const routes: Routes = [{
  path: '',
  component: MedidasHomeComponent
  }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MedidasRoutingModule { }
