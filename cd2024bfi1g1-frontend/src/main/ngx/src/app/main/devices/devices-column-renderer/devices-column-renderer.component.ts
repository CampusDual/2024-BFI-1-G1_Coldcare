import { Component, OnInit, ViewChild, TemplateRef } from '@angular/core';
import { OBaseTableCellRenderer } from 'ontimize-web-ngx';

@Component({
  selector: 'app-devices-column-renderer',
  templateUrl: './devices-column-renderer.component.html',
  styleUrls: ['./devices-column-renderer.component.css']
})
export class DevicesColumnRendererComponent extends OBaseTableCellRenderer implements OnInit {

  @ViewChild('templateref', { read: TemplateRef, static: false }) public templateref: TemplateRef<any>;

  isRed = true;

  TIME: number = 15 * 60 * 1000;

  calculateElapsedGreaterThan15Min(cellVal): void {
    const currentDate: number = new Date().getTime();
    const dif: number = currentDate - cellVal;
    this.isRed = dif > this.TIME;
  }

  getCellData(value: any) {
    this.calculateElapsedGreaterThan15Min(value);
    return value;
  }

}
