import { Component, TemplateRef, ViewChild } from '@angular/core';
import { OBaseTableCellRenderer } from 'ontimize-web-ngx';

@Component({
  selector: 'alert-boolean-renderer',
  templateUrl: './alert-boolean-renderer.component.html',
  styleUrls: ['./alert-boolean-renderer.component.css']
})
export class AlertBooleanRendererComponent extends OBaseTableCellRenderer {

  @ViewChild('templateref', { read: TemplateRef }) public templateref: TemplateRef<any>;

}
