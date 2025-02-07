import { Component, TemplateRef, ViewChild } from '@angular/core';
import { OBaseTableCellRenderer } from 'ontimize-web-ngx';

@Component({
  selector: 'app-containers-lots-boolean-renderer',
  templateUrl: './containers-lots-boolean-renderer.component.html',
  styleUrls: ['./containers-lots-boolean-renderer.component.css']
})
export class ContainersLotsBooleanRendererComponent extends OBaseTableCellRenderer {

  @ViewChild('templateref', { read: TemplateRef }) public templateref: TemplateRef<any>;

  isDateInRange(startDate: string, endDate: string): boolean {
    const currentDate = new Date();
    const start = new Date(startDate);
    const end = new Date(endDate);

    return currentDate >= start && currentDate <= end;
  }

}
