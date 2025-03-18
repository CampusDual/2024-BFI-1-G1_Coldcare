import { Component, ViewChild } from '@angular/core';
import { OTableComponent } from 'ontimize-web-ngx';

@Component({
  selector: 'app-lots-home',
  templateUrl: './lots-home.component.html',
  styleUrls: ['./lots-home.component.css']
})
export class LotsHomeComponent {

  @ViewChild('lotsTable', { static: false }) lotsTable!: OTableComponent;

  fillData() {
    let array = this.lotsTable.getDataArray();
    for (let i = 0; i < array.length; i++) {
      if (array[i].HAS_REPITED === undefined)
        array[i].HAS_REPITED = "0";
    }
  }
}
