import { Component, OnInit, ViewChild } from '@angular/core';
import { OTableComponent } from 'ontimize-web-ngx';
@Component({
  selector: 'app-dwu-home',
  templateUrl: './dwu-home.component.html',
  styleUrls: ['./dwu-home.component.css']
})

export class DwuHomeComponent implements OnInit {
  @ViewChild('myTable', { static: true }) table: OTableComponent;

  ngOnInit(): void {
    this.table.filterData({ usr_id: null });
  }
}
