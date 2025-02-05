import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuardService } from 'ontimize-web-ngx';

import { MainComponent } from './main.component';
import { ProfileComponent } from './profile/profile.component';

export const routes: Routes = [
  {
    path: '',
    component: MainComponent,
    canActivate: [AuthGuardService],
    children: [
      { path: '', redirectTo: 'devices', pathMatch: 'full' },
      { path: 'home', loadChildren: () => import('./home/home.module').then(m => m.HomeModule) },
      { path: 'admin', loadChildren: () => import('./admin/admin.module').then(m => m.AdminModule) },
      { path: 'settings', loadChildren: () => import('./settings/settings.module').then(m => m.SettingsModule) },
      { path: 'profile', component: ProfileComponent },
      { path: 'devices', loadChildren: () => import('./devices/devices.module').then(m => m.DevicesModule) },
      { path: 'containers', loadChildren: () => import('./containers/containers.module').then(m => m.ContainersModule) },
      { path: 'lots', loadChildren: () => import('./lots/lots.module').then(m => m.LotsModule) },
      { path: 'locations', loadChildren: () => import('./locations/locations.module').then(m => m.LocationsModule) },
      { path: 'vehicles', loadChildren: () => import('./vehicles/vehicles.module').then(m => m.VehiclesModule) }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MainRoutingModule { }
