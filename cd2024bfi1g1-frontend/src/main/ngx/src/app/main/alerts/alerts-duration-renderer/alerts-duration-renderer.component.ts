import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { OBaseTableCellRenderer } from 'ontimize-web-ngx';

@Component({
  selector: 'app-alerts-duration-renderer',
  templateUrl: './alerts-duration-renderer.component.html',
  styleUrls: ['./alerts-duration-renderer.component.css']
})
export class AlertsDurationRendererComponent extends OBaseTableCellRenderer implements OnInit {
  @ViewChild('templateref', { read: TemplateRef, static: false }) public templateref: TemplateRef<any>;

  getCellData(rowvalue: any): string {

    let totalSeconds: number;

    if (rowvalue["ALT_DATE_END"] === undefined) {
      const now = new Date;
      totalSeconds = Math.floor((now.getTime() - new Date(rowvalue["ALT_DATE_INIT"]).getTime()) / 1000);
    } else {
      totalSeconds = Math.floor((rowvalue["ALT_DATE_END"] - rowvalue["ALT_DATE_INIT"]) / 1000);
    }

    const days = Math.floor(totalSeconds / (24 * 3600));
    const hours = Math.floor((totalSeconds % (24 * 3600)) / 3600);
    const minutes = Math.floor((totalSeconds % 3600) / 60);
    const secs = totalSeconds % 60;

    const formattedTime = `${String(hours).padStart(2, '0')}h ${String(minutes).padStart(2, '0')}m ${String(secs).padStart(2, '0')}s`;

    if (days > 0) {
      return `${days}d ${formattedTime}`;
    }

    return formattedTime;
  }
}
