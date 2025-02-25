import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { OIntegerInputComponent } from 'ontimize-web-ngx';

@Component({
  selector: 'app-containers-lots-new',
  templateUrl: './containers-lots-new.component.html',
  styleUrls: ['./containers-lots-new.component.css']
})
export class ContainersLotsNewComponent {

  @ViewChild('inputCNT', { static: false }) inputCNT!: OIntegerInputComponent;

  constructor(private route: ActivatedRoute) { }

  setValue() {
    this.route.queryParamMap.subscribe(params => {
      let cntID = params.get('CNT_ID');
      this.inputCNT.setData(cntID);
    });
  }
}
