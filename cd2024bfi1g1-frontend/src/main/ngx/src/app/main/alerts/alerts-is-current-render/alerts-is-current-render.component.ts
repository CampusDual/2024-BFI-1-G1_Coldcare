import { Component, TemplateRef, ViewChild } from '@angular/core';
import { OBaseTableCellRenderer } from 'ontimize-web-ngx';

@Component({
  selector: 'app-alerts-is-current-render',
  templateUrl: './alerts-is-current-render.component.html',
  styleUrls: ['./alerts-is-current-render.component.css']
})
export class AlertsIsCurrentRenderComponent extends OBaseTableCellRenderer{

  @ViewChild('templateref', { read: TemplateRef }) public templateref: TemplateRef<any>;

  isCurrent(endDate: string): boolean {
    return (endDate == null);
  }

}

