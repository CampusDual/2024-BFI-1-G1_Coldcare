import { Component, TemplateRef, ViewChild } from '@angular/core';
import { OBaseTableCellRenderer } from 'ontimize-web-ngx';

@Component({
  selector: 'in-date-range-boolean-renderer',
  templateUrl: './in-date-range-boolean-renderer.component.html',
  styleUrls: ['./in-date-range-boolean-renderer.component.css']
})
export class InDateRangeBooleanRendererComponent extends OBaseTableCellRenderer {

  @ViewChild('templateref', { read: TemplateRef }) public templateref: TemplateRef<any>;

  isDateInRange(startDate: string, endDate: string): boolean {
    const currentDate = new Date();
    const start = new Date(startDate);
    const end = new Date(endDate);

    return (currentDate >= start && (endDate === undefined)) || (currentDate >= start && currentDate <= end);
  }

}
