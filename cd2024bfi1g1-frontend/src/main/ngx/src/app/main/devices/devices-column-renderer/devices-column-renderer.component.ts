import { Component, OnInit, ViewChild, TemplateRef } from '@angular/core';
import { OBaseTableCellRenderer } from 'ontimize-web-ngx';

@Component({
  selector: 'app-devices-column-renderer',
  templateUrl: './devices-column-renderer.component.html',
  styleUrls: ['./devices-column-renderer.component.css']
})
export class DevicesColumnRendererComponent extends OBaseTableCellRenderer implements OnInit {

  @ViewChild('templateref', { read: TemplateRef, static: false }) public templateref: TemplateRef<any>;

  TIME: number = 15 * 60 * 1000;

  calculateElapsedGreaterThan15Min(cellVal): boolean {
    const currentDate: number = new Date().getTime();
    const dif: number = currentDate - cellVal;
    return dif > this.TIME;
  }

  isDeviceStateFalse(rowValue: any): boolean {
    return rowValue.DEV_STATE === false;
  }

  getCellData(value: any) {
    return value;
  }

}
