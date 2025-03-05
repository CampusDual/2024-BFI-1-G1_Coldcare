import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { BillsHomeComponent } from "./bills-home/bills-home.component";
import { BillsDetailsComponent } from './bills-details/bills-details.component';

const routes: Routes = [
    { path: "", component: BillsHomeComponent, },
    { path: ':BIL_ID', component: BillsDetailsComponent },
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule],
})
export class BillsRoutingModule { }
