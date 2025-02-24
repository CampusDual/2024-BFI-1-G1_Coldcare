import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PlansHomeComponent } from './plans-home/plans-home.component';
import { PlansNewComponent } from './plans-new/plans-new.component';
import { PlansDetailComponent } from './plans-detail/plans-detail.component';
import { PlansPricesNewComponent } from './plans-prices-new/plans-prices-new.component';

const routes: Routes = [
    { path: '', component: PlansHomeComponent },
    { path: 'new', component: PlansNewComponent },
    { path: ':PLN_ID', component: PlansDetailComponent },
    { path: ':PLN_ID/:new', component: PlansPricesNewComponent }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class PlansRoutingModule { }
