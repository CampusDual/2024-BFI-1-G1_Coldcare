import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { BillsHomeComponent } from "./bills-home/bills-home.component";

const routes: Routes = [
    {
        path: "",
        component: BillsHomeComponent,
    },
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule],
})
export class BillsRoutingModule { }
