import { AfterViewInit, Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { OFormComponent } from 'ontimize-web-ngx';

@Component({
  selector: 'app-containers-details',
  templateUrl: './containers-details.component.html',
  styleUrls: ['./containers-details.component.css']
})

export class ContainersDetailsComponent implements AfterViewInit {

  @ViewChild('miInput', { static: false }) miInput!: ElementRef;
  @ViewChild('formCNT', { static: false }) formCNT!: OFormComponent;

  constructor(
    private router: Router
  ) { }

  ngAfterViewInit() {
    this.formCNT.onDataLoaded.subscribe(() => this.fillData());

  }

  fillData() {
    const spanElement = this.miInput.nativeElement.querySelector('span');
    if (spanElement) {
      spanElement.textContent = 'Valor por defecto';
    }
  }

  public openContainersLots(selected: any) {
    const row = selected.row;
    if (row && row.CL_ID) {
      this.router.navigate(['main', 'containers-lots', row.CL_ID], { queryParams: { isdetail: true } });
    }
  }
}