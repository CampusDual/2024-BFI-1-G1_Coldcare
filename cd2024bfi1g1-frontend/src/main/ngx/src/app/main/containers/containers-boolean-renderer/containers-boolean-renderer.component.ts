import { Component, TemplateRef, ViewChild } from '@angular/core';
import { OBaseTableCellRenderer } from 'ontimize-web-ngx';

@Component({
  selector: 'app-containers-boolean-renderer',
  templateUrl: './containers-boolean-renderer.component.html',
  styleUrls: ['./containers-boolean-renderer.component.css']
})
export class ContainersBooleanRendererComponent extends OBaseTableCellRenderer {

  @ViewChild('templateref', { read: TemplateRef }) public templateref: TemplateRef<any>;

}
