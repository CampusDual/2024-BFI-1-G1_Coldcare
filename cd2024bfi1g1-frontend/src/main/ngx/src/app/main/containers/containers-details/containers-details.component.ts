import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-containers-details',
  templateUrl: './containers-details.component.html',
  styleUrls: ['./containers-details.component.css']
})

export class ContainersDetailsComponent {

  constructor(
    private router: Router
  ) { }

  public openContainersLots(selected: any) {
    const row = selected.row;
    if (row && row.CL_ID) {
      this.router.navigate(['main', 'containers-lots', row.CL_ID], { queryParams: { isdetail: true } });
    }
  }
}