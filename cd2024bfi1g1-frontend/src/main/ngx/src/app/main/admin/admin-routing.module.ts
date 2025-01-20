import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  { path: 'roles', loadChildren: () => import('./roles/roles.module').then(m => m.RolesModule) },
  { path: 'users', loadChildren: () => import('./users/users.module').then(m => m.UsersModule) },
  { path: 'devices-without-users', loadChildren: () => import('./devices-without-users/devices-without-users.module').then(m => m.DevicesWithoutUsersModule) },
  { path: 'medidas', loadChildren: () => import('./medidas/medidas.module').then(m => m.MedidasModule), },
  { path: 'companies', loadChildren: () => import('./companies/companies.module').then(m => m.CompaniesModule) }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
